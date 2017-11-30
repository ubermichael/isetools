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
package ca.nines.ise.validator.node.attribute;

import ca.nines.ise.log.Message;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.schema.Attribute;
import ca.nines.ise.validator.node.AttributeValidator;
import com.neovisionaries.i18n.LanguageCode;

/**
 * Validate FOREIGN/@lang attributes.
 */
public class LangAttributeValidator implements AttributeValidator {

    @Override
    public void validate(TagNode n, Attribute attr) {
        if( ! (n instanceof StartNode)) {
            return;
        }
        if( ! n.getName().toLowerCase().equals("foreign")) {
            return;
        }
        String lang = n.getAttribute("lang");
        if(lang == null) {
            return;
        }
        LanguageCode lc = LanguageCode.getByCode(lang, true);
        if(lc != null) {
            return;
        }
        Message m = Message.builder("validator.attribute.badlanguage")
                .fromNode(n)
                .addNote("Attribute @lang='" + lang + "' does not look like a valid language.")
                .build();
        Log.addMessage(m);
    }

}
