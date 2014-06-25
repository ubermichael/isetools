/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Message;
import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.schema.Attribute;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Abstract class to handle the commonalities in tag validations. Also provides
 * attribute validation.
 * <p>
 * @author Michael Joyce <ubermichael@gmail.com>
 * @param <T>
 */
abstract public class TagNodeValidator<T extends TagNode> extends NodeValidator<T> {

  /**
   * Construct a tag node validator.
   * <p>
   * @param schema The schema for validation.
   */
  public TagNodeValidator(Schema schema) {
    super(schema);
  }

  @Override
  abstract public void validate(T node);

  /**
   * Validate a string attribute.
   * <p>
   * Validations performed:
   * <p>
   * <ul>
   * <li>The string must include at least one non-whitespace character.</li>
   * </ul>
   * <p>
   * @param n    TagNode to validate
   * @param attr attribute to validate against
   */
  @ErrorCode(code = {
    "validator.attribute.badstring"
  })
  public void validate_attribute_string(TagNode n, Attribute attr) {
    String value = n.getAttribute(attr.getName());
    // @todo use StringUtils.isWhitespace instead. faster.
    if (value.matches("^\\s*$")) {
      Message m = log.error("validator.attribute.badstring", n);
      m.addNote("Attribute " + attr.getName() + "=\"" + value + "\" only contains whitespace.");
    }
  }

  /**
   * Validate a number attribute.
   * <p>
   * Validations performed:
   * <p>
   * <ul>
   * <li>The attribute must be a number, by matching against the regular
   * expression {@code "^[+-]?\\d+(\\.\\d+)?$"}</li>
   * </ul>
   * <p>
   * @param n    TagNode to validate
   * @param attr attribute to validate against
   */
  @ErrorCode(code = {
    "validator.attribute.badnumber"
  })
  public void validate_attribute_number(TagNode n, Attribute attr) {
    String value = n.getAttribute(attr.getName());
    if (!value.matches("^[+-]?\\d+(\\.\\d+)?$")) {
      Message m = log.error("validator.attribute.badnumber", n);
      m.addNote("Attribute " + attr.getName() + "=" + value + " does not look like a number.");
    }
  }

  /**
   * Validate a list attribute.
   * <p>
   * Validations performed:
   * <p>
   * <ul>
   * <li>The attribute value is treated as a comma separated list, and each item
   * in the list must be one of the allowed values.</li>
   * </ul>
   * <p>
   * @param n    TagNode to validate
   * @param attr attribute to validate against
   */
  @ErrorCode(code = {
    "validator.attribute.badlist"
  })
  public void validate_attribute_list(TagNode n, Attribute attr) {
    String values[] = n.getAttribute(attr.getName()).split(", ?");
    String[] options = attr.getOptions();
    for (String value : values) {
      if (!ArrayUtils.contains(options, value)) {
        Message m = log.error("validator.attribute.badlist", n);
        m.addNote("Attribute " + attr.getName() + " cannot contain " + value);
      }
    }
  }

  /**
   * Validate a select attribute.
   * <p>
   * Validations performed:
   * <p>
   * <ul>
   * <li>The attribute value must be one of the allowed values.</li>
   * </ul>
   * <p>
   * @param n    TagNode to validate
   * @param attr attribute to validate against
   */
  @ErrorCode(code = {
    "validator.attribute.badselect"
  })
  public void validate_attribute_select(TagNode n, Attribute attr) {
    String value = n.getAttribute(attr.getName());
    String[] options = attr.getOptions();
    if (!ArrayUtils.contains(options, value)) {
      Message m = log.error("validator.attribute.badselect", n);
      m.addNote("Attribute " + attr.getName() + " cannot contain " + value);
    }
  }

  /**
   * Validate an attribute, by calling one of the validate_attribute_*
   * functions.
   * <p>
   * Validations performed:
   * <p>
   * <ul>
   * <li>The attribute type (as defined in the schema) must be defined.</li>
   * </ul>
   * <p>
   * @param n    TagNode to validate
   * @param attr attribute to validate against
   */
  @ErrorCode(code = {
    "validator.attribute.unknowntype"
  })
  public void validate_attribute(TagNode n, Attribute attr) {
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
        Message m = log.error("validator.attribute.unknowntype", n);
        m.addNote("The unknown attribute is " + attr.getType() + " defined on " + n.getName());
    }
  }

  /**
   * Validate all the attributes in a tag node.
   * <p>
   * Validations performed:
   * <p>
   * <ul>
   * <li>Each attribute name must be defined for the tag.</li>
   * <li>The attribute must not be depreciated.</li>
   * <li>The attribute may be empty, if allowed by the schema.</li>
   * </ul>
   * <p>
   * Additionally, each of the attributes defined as required for the tag must
   * be present.
   * <p>
   * Warning: validate_attributes will silently ignore nodes which do not have a
   * definition in the schema. It is the responsibility of calling classes to
   * check that the schema contains a definition or the node.
   * <p>
   * @param n TagNode to validate
   */
  @ErrorCode(code = {
    "validator.attribute.unknown",
    "validator.attribute.depreciated",
    "validator.attribute.nonempty",
    "validator.attribute.missing",
  })
  public void validate_attributes(TagNode n) {
    String tagName = n.getName();
    Tag tag = schema.getTag(tagName);
    Message m;

    if (tag == null) {
      return;
    }

    // validate the attributes found on the tag in the SGMLish.
    for (String name : n.getAttributeNames()) {
      Attribute attr = tag.getAttribute(name);
      if (attr == null) {
        m = log.error("validator.attribute.unknown", n);
        m.addNote("The schema does not define attribute " + name + " for tag " + tagName + ".");
        continue;
      }
      if (attr.isDepreciated()) {
        m = log.error("validator.attribute.depreciated", n);
        m.addNote(attr.getDepreciated());
        continue;
      }
      String attrValue = n.getAttribute(name);
      if (attrValue.length() == 0) {

        if (attr.isEmpty()) {
          continue;
        }

        m = log.error("validator.attribute.nonempty", n);
        m.addNote("Attribute " + name + " must not be empty for tag " + tagName + ".");
        continue;
      }
      validate_attribute(n, attr);
    }
    for (String attrName : tag.getAttributeNames()) {
      Attribute attr = tag.getAttribute(attrName);
      if (attr.isOptional()) {
        continue;
      }
      String attrValue = n.getAttribute(attrName);
      if (attrValue == null) {
        m = log.error("validator.attribute.missing", n);
        m.addNote("Attribute " + attrName + " is required for " + tagName + " tags");
      }
    }

  }
}
