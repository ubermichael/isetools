/*
 * Copyright (C) 2016 Malcolm Moran <malcolm.moran@outlook.com>
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
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TextNode;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.schema.Tag;

/**
 * 
 * Checks to see if certain elements contain a newline character and, therefore, span multiple lines
 * ex:
 *    <ORNAMENT>\n</ORNAMENT> => spanned a line
 *    <ORNAMENT>valid</ORANMENT> => didn't span a line
 * 
 * @author Malcolm Moran <malcolm.moran@outlook.com>
 */
public class SpanLineValidator {
  private final Schema schema;

  public SpanLineValidator(Schema schema) {
      this.schema = schema;
  }
  
  StartNode start;
  
  @ErrorCode(code = {
    "validator.spanLine.spannedLines"
  })
  
  private void process_start(StartNode n) {
    Tag t = schema.getTag(n.getName());
    if (t != null && t.getSpansLines().equals("no"))
      start = n;
  }
  
  private void process_end(EndNode n) {
    Tag t = schema.getTag(n.getName());
    if (t != null && t.getSpansLines().equals("no"))
      start = null;
  }
  
  @ErrorCode(code = {
      "validator.spanLine.spannedLines"
  })
  private void process_text(TextNode n) {
    if (start != null && n.getText().contains("\n")){
      Message m = Message.builder("validator.spanLine.spannedLines")
          .fromNode(n)
          .addNote("Tag " + start.getName() + " spans more than one line")
          .build();
      Log.addMessage(m);
    }
  }
  
  public void validate(DOM dom) {
    start = null;
    
    for (Node n : dom) {
      switch (n.type()) {
        case END:
          process_end((EndNode) n);
          break;
        case START:
          process_start((StartNode) n);
          break;        
        case TEXT:
          process_text((TextNode) n);
          break;
      }
    }
  }

}