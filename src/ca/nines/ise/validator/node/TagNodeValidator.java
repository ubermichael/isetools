/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Message;
import ca.nines.ise.misc.ErrorCode;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.schema.Attribute;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 * @param <T>
 */
abstract public class TagNodeValidator<T extends TagNode> extends NodeValidator<T> {

  public TagNodeValidator(Schema schema) {
    super(schema);
  }

  @ErrorCode(code={
    "validator.attribute.badstring"
  })
  public void validate_attribute_string(T n, Attribute attr) {
    String value = n.getAttribute(attr.getName());
    // @todo use StringUtils.isWhitespace instead. faster.
    if (value.matches("^\\s*$")) {
      Message m = error("validator.attribute.badstring", n);
      m.addNote("Attribute " + attr.getName() + "=\"" + value + "\" only contains whitespace.");
    }
  }

  @ErrorCode(code={
    "validator.attribute.badnumber"
  })
  public void validate_attribute_number(T n, Attribute attr) {
    String value = n.getAttribute(attr.getName());
    if (!value.matches("^[+-]?\\d+(\\.\\d+)?$")) {
      Message m = error("validator.attribute.badnumber", n);
      m.addNote("Attribute " + attr.getName() + "=" + value + " does not look like a number.");
    }
  }

  @ErrorCode(code={
    "validator.attribute.badlist"
  })
  public void validate_attribute_list(T n, Attribute attr) {
    String values[] = n.getAttribute(attr.getName()).split(", ?");
    String[] options = attr.getOptions();
    for (String value : values) {
      if (!ArrayUtils.contains(options, value)) {
        Message m = error("validator.attribute.badlist", n);
        m.addNote("Attribute " + attr.getName() + " cannot contain " + value);
      }
    }
  }

  @ErrorCode(code={
    "validator.attribute.badselect"
  })
  public void validate_attribute_select(T n, Attribute attr) {
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
  public void validate_attribute_value(T n, Attribute attr) {
    switch (attr.getType()) {
      case "string":
        validate_attribute_string(n, attr);
        break;
      case "number":
        validate_attribute_number(n, attr);
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
  public void validate_attributes(T n) {
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
      if (attrValue.length() == 0) {
        
        if(attr.isEmpty()) {
          continue;
        }
        
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
}
