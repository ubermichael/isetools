/*
 * Copyright (C) 2015 Malcolm Moran <malcolm.moran@outlook.com>
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
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.NodeType;
/**
 * 
 * @author Malcolm Moran <malcolm.moran@outlook.com>
 * 
 * Checks to see if certain elements contain a newline character,
 * and, therefore, span multiple lines
 */
public class SpanLineValidator {

  @ErrorCode(code = {
    "validator.spanLine.spannedLines"
  })
  private void process_end(EndNode n) {
    if (n.getText().contains("\n")){
      Message m = Message.builder("validator.spanLine.spannedLines")
          .fromNode(n)
          .addNote("Tag " + n.getName() + " spans more than one line")
          .build();
      Log.addMessage(m);
    }
  }

  public void validate(DOM dom) {
    for (Node n : dom) {
      if (n.type() == NodeType.END) {
        switch(n.getName()){
          case "ornament":
            process_end((EndNode) n);
            break;
        }
      }
    }
  }

}