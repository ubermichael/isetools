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
package ca.nines.ise.transformer;

import ca.nines.ise.node.NodeType;
import ca.nines.ise.node.TextNode;
import ca.nines.ise.util.TextReplacementTable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Normalize an ISE document, by combining adjacent text nodes and fixing up
 * newlines.
 *

 */
public class Text extends IdentityTransform {

    private static TextReplacementTable tbl = null;
    
    public Text() {
        super();
        if(tbl == null) {
            try {
                tbl = TextReplacementTable.defaultTextReplacementTable();
            } catch (IOException ex) {
                Logger.getLogger(Text.class.getName()).log(Level.SEVERE, "Cannot load default text replacement table.", ex);
            }
        }
    }
    
    /**
     * Transform a text node, by appending the next text node if there is one.
     *
     * TODO I wonder if it works...
     *
     * @param n
     */
    public void text(TextNode n) {
        String s = n.getText();
        for(String src : tbl.getSources()) {
            s = s.replace(src, tbl.getReplacement(src));
        }
        TextNode txt = new TextNode(n);
        txt.setText(s);
        dom.add(txt);
    }

}
