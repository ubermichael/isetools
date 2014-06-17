/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.misc.ErrorCode;
import ca.nines.ise.node.AbbrNode;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.CommentNode;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.node.TextNode;
import ca.nines.ise.schema.Attribute;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;

import java.io.IOException;
import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.apache.commons.lang3.ArrayUtils;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class TagValidator {

  private final Schema schema;
  private final Log log;
  private DOM dom;

  public TagValidator() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    log = Log.getInstance();
    schema = new Schema();
  }

  @ErrorCode(code = "validator.abbr.depreciated")
  public void validate(AbbrNode n) {
    Message m = error("validator.abbr.depreciated", n);
    if (n.getText().length() > 12) {
      m = error("validator.abbr.long", n);
      m.addNote("The long abbreviation starts with " + n.getText().substring(0, 12));
      m.addNote("The abbreviation cannot be corrected automatically.");
    }
  }

  public void validate(CharNode n) {
    // @TODO figure this out.
  }

  @ErrorCode(code = {
    "validator.comment.badstart",
    "validator.comment.badend",
    "validator.comment.dashes"
  })
  public void validate(CommentNode n) {
    Message m = null;
    String text = n.getText();
    if (!text.startsWith("<!--")) {
      m = error("validator.comment.badstart", n);
      m.addNote("The comment started with " + text.substring(0, 4));
    }
    if (!text.endsWith("-->")) {
      m = error("validator.comment.badend", n);
      m.addNote("The comment ended with " + text.substring(text.length() - 3));
    }
    text = text.replaceAll("^(<!--)|(-->)$", "");
    if (text.contains("--")) {
      m = error("validator.comment.dashes", n);
      m.addNote("after replace: " + text);
    }
  }

  @ErrorCode(code = {
    "validator.tag.unknown",
    "validator.tag.endempty"
  })
  public void validate(EndNode n) {
    Tag t = schema.getTag(n.getName());
    if (t == null) {
      Message m = error("validator.tag.unknown", n);
      m.addNote("Tag " + n.getName() + " is not defined in the schema.");
      return;
    }
    if (t.isEmpty()) {
      Message m = error("validator.tag.endempty", n);
      m.addNote("End tag " + n.getName() + " should not occur.");
    }
  }

  public void validate_attribute_string(TagNode n, Attribute attr) {
    String value = n.getAttribute(attr.getName());
    if (value.matches("^\\s*$")) {
      Message m = error("validator.attribute.badstring", n);
      m.addNote("Attribute " + attr.getName() + "=\"" + value + "\" only contains whitespace.");
    }
  }

  public void validate_attribute_number(TagNode n, Attribute attr) {
    String value = n.getAttribute(attr.getName());
    if (!value.matches("^[+-]?\\d+(\\.\\d+)?$")) {
      Message m = error("validator.attribute.badnumber", n);
      m.addNote("Attribute " + attr.getName() + "=" + value + " does not look like a number.");
    }
  }

  public void validate_attribute_match(TagNode n, Attribute attr) {
    String value = n.getAttribute(attr.getName());
    if (!value.matches(attr.getMatch())) {
      Message m = error("validator.attribute.badmatch", n);
      m.addNote("Attribute " + attr.getName() + " does not match the regular expression " + attr.getMatch() + ".");
    }
  }

  public void validate_attribute_list(TagNode n, Attribute attr) {
    String values[] = n.getAttribute(attr.getName()).split(", ?");
    String[] options = attr.getOptions();
    for (String value : values) {
      if (!ArrayUtils.contains(options, value)) {
        Message m = error("validator.attribute.badlist", n);
        m.addNote("Attribute " + attr.getName() + " cannot contain " + value);
      }
    }
  }

  public void validate_attribute_select(TagNode n, Attribute attr) {
    String value = n.getAttribute(attr.getName());
    String[] options = attr.getOptions();
    if (!ArrayUtils.contains(options, value)) {
      Message m = error("validator.attribute.badselect", n);
      m.addNote("Attribute " + attr.getName() + " cannot contain " + value);
    }
  }

  @ErrorCode(code = {
    "validator.attribute.unknowntype"
  })
  public void validate_attribute_value(TagNode n, Attribute attr) {
    switch (attr.getType()) {
      case "string":
        validate_attribute_string(n, attr);
        break;
      case "number":
        validate_attribute_number(n, attr);
        break;
      case "match":
        validate_attribute_match(n, attr);
        break;
      case "list":
        validate_attribute_list(n, attr);
        break;
      case "select":
        validate_attribute_select(n, attr);
        break;
      default:
        Message m = error("validator.attribute.unknowntype", n);
        m.addNote("The unknown attribute is " + attr.getType() + " defined on " + n.getName());
    }
  }

  @ErrorCode(code = {
    "validator.attribute.unknown",
    "validator.attribute.depreciated",
    "validator.attribute.nonempty"
  })
  public void validate_attributes(TagNode n) {
    String tagName = n.getName();
    Tag tag = schema.getTag(tagName);
    Message m;

    // validate the attributes found on the tag in the SGMLish.
    for (String name : n.getAttributeNames()) {
      Attribute attr = tag.getAttribute(name);
      if (attr == null) {
        m = error("validator.attribute.unknown", n);
        m.addNote("The schema does not define attribute " + name + " for tag " + tagName + ".");
        continue;
      }
      if (attr.isDepreciated()) {
        m = error("validator.attribute.depreciated", n);
        m.addNote(attr.getDepreciated());
        continue;
      }
      String attrValue = n.getAttribute(name);
      if ((attrValue.length() == 0) && (!attr.isEmpty())) {
        m = error("validator.attribute.nonempty", n);
        m.addNote("Attribute " + name + " must not be empty for tag " + tagName + ".");
        continue;
      }
      validate_attribute_value(n, attr);
    }

    for (String attrName : tag.getAttributeNames()) {
      Attribute attr = tag.getAttribute(attrName);
      if (attr.isOptional()) {
        continue;
      }
      String attrValue = n.getAttribute(attrName);
      if (attrValue == null) {
        m = error("validator.attribute.missing", n);
        m.addNote("Attribute " + attrName + " is required for " + tagName + " tags");
        continue;
      }
    }

  }

  @ErrorCode(code = {
    "validator.tag.unknown",
    "validator.tag.emptystart",
    "validator.tag.depreciated"
  })
  public void validate(EmptyNode n) {
    Tag t = schema.getTag(n.getName());
    if (t == null) {
      Message m = error("validator.tag.unknown", n);
      m.addNote("Tag " + n.getName() + " is not defined in the schema.");
      return;
    }
    if (!t.maybeEmpty()) {
      Message m = error("validator.tag.emptystart", n);
      m.addNote("Tag " + n.getName() + " should not be self-closing.");
    }
    if (t.isDepreciated()) {
      Message m = error("validator.tag.depreciated", n);
      m.addNote(t.getDepreciated());
    }
    validate_attributes(n);
  }

  @ErrorCode(code = {
    "validator.tag.unknown",
    "validator.tag.startempty",
    "validator.tag.depreciated"
  })
  public void validate(StartNode n) {
    Tag t = schema.getTag(n.getName());
    if (t == null) {
      Message m = error("validator.tag.unknown", n);
      m.addNote("Tag " + n.getName() + " is not defined in the schema.");
      return;
    }
    if (t.isEmpty()) {
      Message m = error("validator.tag.startempty", n);
      m.addNote("Start tag " + n.getName() + " should be self-closing.");
    }
    if (t.isDepreciated()) {
      Message m = error("validator.tag.depreciated", n);
      m.addNote(t.getDepreciated());
    }
    validate_attributes(n);
  }

  @ErrorCode(code = {
    "validator.tag.depreciatedhash",})
  public void validate(TextNode n) {
    if (n.getText().contains("#")) {
      error("validator.text.depreciatedhash", n);
    }
  }

  private Message error(String code, Node n) {
    Message m = log.add(code);
    m.setComponent(this.getClass().getCanonicalName());
    m.setSource(n.getSource());
    m.setColumn(n.getColumn());
    m.setLine(n.getLine());
    m.setTLN(n.getTln());
    m.addNote(dom.getLine(n.getLine() - 1));
    return m;
  }

  public void validate(DOM dom) throws Exception {
    Iterator<Node> iterator = dom.iterator();
    this.dom = dom;

    while (iterator.hasNext()) {
      Node n = iterator.next();
      switch (n.type()) {
        case "#ABBR":
          validate((AbbrNode) n);
          break;
        case "#CHAR":
          validate((CharNode) n);
          break;
        case "#COMMENT":
          validate((CommentNode) n);
          break;
        case "#EMPTY":
          validate((EmptyNode) n);
          break;
        case "#END":
          validate((EndNode) n);
          break;
        case "#START":
          validate((StartNode) n);
          break;
        case "#TEXT":
          validate((TextNode) n);
          break;
        default:
          throw new Exception("Unknown node type " + n.type());
      }
    }
  }

}
