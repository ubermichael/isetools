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
package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;
import java.util.Arrays;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tag node.
 * <p>
 * @author Michael Joyce <ubermichael@gmail.com>
 */
abstract public class TagNode extends Node {

    private static final Logger logger = Logger.getLogger(TagNode.class.getName());

    /**
     * Name,value pairs for attributes.
     */
    protected AttributeSet attributes;

    /**
     * Name of the tag.
     */
    protected String tagname;

    /**
     * Construct a tag node with no name or attributes.
     */
    public TagNode() {
        super();
        this.tagname = "";
        this.attributes = new AttributeSet();
    }

    /**
     * Copy constructor.
     */
    public TagNode(Node n) {
        super(n);
        this.attributes = new AttributeSet();
        if (n instanceof TagNode) {
            TagNode tn = (TagNode) n;
            this.tagname = tn.tagname;
            this.attributes = new AttributeSet();
            for(String name : tn.getAttributeNames()) {
                this.attributes.setAttribute(name, tn.getAttribute(name));
            }
        }
    }

    /**
     * Create a new tag node with a name.
     */
    public TagNode(String tagname) {
        this.attributes = new AttributeSet();
        this.tagname = tagname;
    }

    /**
     * Remove all the attributes on a tag.
     */
    public void clearAttributes() {
        if (attributes.hasAttribute("n") && ownerDom != null) {
            ownerDom.requestReindex();
        }
        attributes.clearAttributes();
    }

    /**
     * Delete one attribute.
     * <p>
     * @param name
     */
    public void deleteAttribute(String name) {
        if (attributes.hasAttribute("n")) {
            ownerDom.requestReindex();
        }
        attributes.deleteAttribute(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fragment expanded() {
        Fragment f = new Fragment();
        f.add(this);
        return f;
    }

    /**
     * Get an attribute value. Attribute names are case insensitive.
     * <p>
     * @param name
     * <p>
     * @return String
     */
    public String getAttribute(String name) {
        return attributes.getAttribute(name);
    }

    /**
     * Check if a node has an attribute. Attribute names are case insensitive.
     * <p>
     * @param name
     * <p>
     * @return boolean
     */
    public boolean hasAttribute(String name) {
        return attributes.hasAttribute(name);
    }

    /**
     * Return a list of attribute names, in their original cases.
     * <p>
     * @return String of sorted original-case attribute names.
     */
    public String[] getAttributeNames() {
        String[] names = attributes.getAttributeNames();
        Arrays.sort(names);
        return names;
    }

    /**
     * Return a list of attribute names, in their original cases and order.
     * 
     * @param preserveOrder preserve the original order if true, otherwise 
     * sort the attributes by name.
     * 
     * @return String of sorted original-case attribute names.
     */
    public String[] getAttributeNames(boolean preserveOrder) {
        String[] names = attributes.getAttributeNames();
        return names;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return tagname;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String plain() {
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sgml() {
        StringBuilder sb = new StringBuilder();

        sb.append("<").append(getName());
        for (String name : getAttributeNames()) {
            sb.append(" ").append(name).append('=').append('"').append(getAttribute(name)).append('"');
        }
        if (this instanceof EmptyNode) {
            sb.append(" /");
        }
        sb.append(">");
        return sb.toString();
    }

    /**
     * Set an attribute value. May cause the owner DOM to be reindexed.
     * <p>
     * @param name
     * @param value
     */
    public void setAttribute(String name, String value) {
        if(attributes == null) {
            logger.log(Level.SEVERE, "attributes is null. wtf.");
        }
        attributes.setAttribute(name, value);
        if (name.equalsIgnoreCase("n") && ownerDom != null) {
            ownerDom.requestReindex();
        }
    }

    /**
     * Set the tag name. May cause the owner DOM to be reindexed.
     * <p>
     * @param name
     * <p>
     * @return String the name.
     */
    public String setName(String name) {
        switch (name) {
            case "ACT":
            case "SCENE":
            case "L":
            case "TLN":
                ownerDom.requestReindex();
                break;
        }
        return this.tagname = name;
    }

    /**
     * Return a human friendly string representation.
     * <p>
     * @return String
     */
    @Override
    public String toString() {
        Formatter formatter = new Formatter();
        formatter.format("%s", super.toString());
        formatter.format(":%s(", tagname);
        for (String name : attributes.getAttributeNames()) {
            formatter.format("@%s=%s ", name, attributes.getAttribute(name));
        }
        formatter.format(")");
        return formatter.toString();
    }

    /**
     * Tag nodes don't have a unicode equivalent.
     * <p>
     * @return empty string
     */
    @Override
    public String unicode() {
        return "";
    }

}
