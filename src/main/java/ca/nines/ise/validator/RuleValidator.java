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
package ca.nines.ise.validator;

import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.CommentNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.node.TextNode;
import java.util.Arrays;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class RuleValidator {
    
    @ErrorCode(code = {
        "validator.rule.linenotempty"
    })    
    public void validate(DOM dom) {
        for (Node n : dom) {
            if ((n instanceof TagNode) && ((TagNode) n).getName().toLowerCase().equals("rule")) {
                Node[] line = dom.getParsedLine(n.getLine());
                if(line.length == 1) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (Node node : line) {
                    if (node instanceof CommentNode) {
                        continue;
                    }
                    if ((node instanceof TextNode) && ((TextNode) node).isWs()) {
                        continue;
                    }
                    if ((node instanceof TagNode) && ((TagNode) node).getName().toLowerCase().equals("rule")) {
                        continue;
                    }
                    sb.append(node.getText());
                }
                if(sb.length() > 0) {
                    Message m = Message.builder("validator.rule.linenotempty")
                            .fromNode(n)
                            .addNote("Line also contains " + sb.toString())
                            .build();
                    Log.addMessage(m);
                }
            }
        }
    }
    
}
