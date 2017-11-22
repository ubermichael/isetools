/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.exceptions.AttributeTypeException;
import ca.nines.ise.log.Message;
import ca.nines.ise.log.Log;
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
import java.util.Map;

/**
 * Abstract class to handle the commonalities in tag validations. Also provides
 * attribute validation.
 * <p>

 * @param <T>
 */
abstract public class TagNodeValidator<T extends TagNode> implements NodeValidator<T> {

  /**
   * Mapping of attribute types to attribute validators.
   */
  private static final Map<AttributeType, AttributeValidator> validators;

  static {
    validators = new HashMap<>();
    validators.put(AttributeType.LIST, new ListAttributeValidator());
    validators.put(AttributeType.NUMBER, new NumberAttributeValidator());
    validators.put(AttributeType.SELECT, new SelectAttributeValidator());
    validators.put(AttributeType.STRING, new StringAttributeValidator());
  }

  /**
   * Validate a tag node.
   *
   * @param node The tag node to validate.
   * @param schema The schema to validate against.
   * @throws ca.nines.ise.exceptions.AttributeTypeException if the node contains
   * an attribute of an unknown type.
   */
  @Override
  abstract public void validate(T node, Schema schema) throws AttributeTypeException;

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
   * @param n TagNode to validate
   * @param attr attribute to validate against
   * <p>
   * @throws ca.nines.ise.exceptions.AttributeTypeException if the attribute
   * type is unknown.
   */
  public void validate_attribute(TagNode n, Attribute attr) throws AttributeTypeException {
    AttributeType at = attr.getType();
    AttributeValidator v = TagNodeValidator.validators.get(attr.getType());
    if (v == null) {
      throw new AttributeTypeException("Unknown attribute type: " + at.name());
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
   * <b>Warning</b>: validate_attributes will silently ignore nodes which do not
   * have a definition in the schema. It is the responsibility of calling
   * classes to check that the schema contains a definition or the node.
   * <p>
   * @param n TagNode to validate
   * @param schema Schema to validate against
   * @throws ca.nines.ise.exceptions.AttributeTypeException
   */
  public void validate_attributes(TagNode n, Schema schema) throws AttributeTypeException {
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
        m = Message.builder("validator.attribute.unknown")
                .fromNode(n)
                .addNote("The schema does not define attribute " + name + " for tag " + tagName + ".")
                .build();
        Log.addMessage(m);
        continue;
      }
      if (attr.isDepreciated()) {
        m = Message.builder("validator.attribute.depreciated")
                .fromNode(n)
                .addNote(attr.getDepreciated())
                .build();
        Log.addMessage(m);
        continue;
      }
      String attrValue = n.getAttribute(name);
      if (attrValue.length() == 0) {

        if (attr.isEmpty()) {
          continue;
        }
        m = Message.builder("validator.attribute.nonempty")
                .fromNode(n)
                .addNote("Attribute " + name + " must not be empty for tag " + tagName + ".")
                .build();
        Log.addMessage(m);
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
        m = Message.builder("validator.attribute.missing")
                .fromNode(n)
                .addNote("Attribute " + attrName + " is required for " + tagName + " tags")
                .build();
        Log.addMessage(m);
      }
    }

  }
}
