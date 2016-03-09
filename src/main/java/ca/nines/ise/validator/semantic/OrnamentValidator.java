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
package ca.nines.ise.validator.semantic;

import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.Fragment;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TagNode;
import java.io.IOException;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class OrnamentValidator extends SemanticValidator {

    private void unclosedOrnament(Node n) {
        Message m = Message.builder("validator.ornament.unclosed")
                .fromNode(n)
                .addNote("Cannot validate ornament tag - end tag is missing.")
                .build();
        Log.addMessage(m);
    }

    @ErrorCode(code = {
        "validator.ornament.multiline",
        "validator.ornament.unclosed"
    })

    @Override
    public void validateDOM(DOM dom) throws IOException {
        for (Node n : dom) {
            if (!(n instanceof TagNode)) {
                continue;
            }
            if (!(n instanceof StartNode)) {
                continue;
            }
            if (!n.getName().toLowerCase().equals("ornament")) {
                continue;
            }
            Node next = dom.get(n.getPosition() + 1);
            if (next == null) {
                unclosedOrnament(n);
                continue;
            }

            Node end = dom.findForward(next, "ornament");

            if (!(end instanceof EndNode)) {
                unclosedOrnament(n);
                continue;
            }

            if (n.getLine() != end.getLine()) {
                Message m = Message.builder("validator.rule.multiline")
                        .fromNode(n)
                        .addNote("Ornament tags cannot span multiple lines. This one starts on " + n.getLine() + " and ends on " + end.getLine())
                        .build();
                Log.addMessage(m);
            }
        }
    }

}
