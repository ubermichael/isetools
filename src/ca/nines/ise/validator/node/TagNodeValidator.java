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
import ca.nines.ise.schema.Attribute.AttributeType;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;
import ca.nines.ise.validator.node.attribute.ListAttributeValidator;
import ca.nines.ise.validator.node.attribute.NumberAttributeValidator;
import ca.nines.ise.validator.node.attribute.SelectAttributeValidator;
import ca.nines.ise.validator.node.attribute.StringAttributeValidator;
import java.util.HashMap;

/**
 * Abstract class to handle the commonalities in tag validations. Also provides
 * attribute validation.
 * <p>
 * @author Michael Joyce <michael@negativespace.net>
 * @param <T>
 */
abstract public class TagNodeValidator<T extends TagNode> extends NodeValidator<T> {

  private static final HashMap<AttributeType, AttributeValidator> validators = new HashMap<>();

  static {
    validators.put(AttributeType.LIST, new ListAttributeValidator());
    validators.put(AttributeType.NUMBER, new NumberAttributeValidator());
    validators.put(AttributeType.SELECT, new SelectAttributeValidator());
    validators.put(AttributeType.STRING, new StringAttributeValidator());
  }
  
  /**
   * Construct a tag node validator.
   * <p>
   * @param schema The schema for validation.
   */
  public TagNodeValidator(Schema schema) {
    super(schema);
  }

  @Override
  abstract public void validate(T node) throws Exception;

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
   * @throws java.lang.Exception
   */
  @ErrorCode(code = {
    "validator.attribute.unknowntype"
  })
  public void validate_attribute(TagNode n, Attribute attr) throws Exception {
    AttributeType at = attr.getType();
    AttributeValidator v = TagNodeValidator.validators.get(attr.getType());
    if(v == null) {     
      throw new Exception("Unknown attribute type: " + at.name());
    }
    v.validate(n, attr);
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
  public void validate_attributes(TagNode n) throws Exception {
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
