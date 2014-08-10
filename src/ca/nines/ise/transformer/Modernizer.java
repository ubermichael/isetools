/*
 * Copyright (C) 2014 Michael Joyce <michael@negativespace.net>
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

import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.NodeType;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.node.TextNode;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Modernizer extends Transformer {

  @Override
  public DOM transform(DOM dom) throws IOException {
    DOM mod = new DOM();
    boolean inHW = false;
    Node n;
    mod.setLines(dom.getLines());
    mod.setSource(dom.getSource());
    for (Iterator<Node> it = dom.iterator(); it.hasNext();) {
      Node node = it.next();
      switch (node.getName()) {
        case "#ABBR":
        case "#CHAR":
          String txt = "";
          switch (node.getText()) {
            case "{#}":
              txt = " ";
              break;
            case "{ }":
              break;
            default:
              txt = node.plain();
          }
          n = new TextNode(node);
          n.setText(txt);
          mod.add(new TextNode(n));
          break;
        case "#COMMENT":
          break;
        case "HW":
          if (node.type() == NodeType.START) {
            inHW = true;
          }
          break;
        case "BLL":
          break;
        case "BR":
        case "C":
        case "COL":
          break;
        case "CW":
          skipTo(it, NodeType.END, "CW");
          break;
        case "FONTGROUP":
        case "J":
        case "L":
        case "LS":
        case "ORNAMENT":
        case "PAGE":
        case "PN":
        case "QLN":
        case "R":
        case "RA":
          break;
        case "RT":
          skipTo(it, NodeType.END, "RT");
          break;
        case "RULE":
          break;
        case "SP":
          mod.add(node);
          n = new TextNode(node);
          n.setText(((TagNode) node).getAttribute("norm"));
          mod.add(n);
          n = new EndNode(node);
          ((TagNode) n).setName("SP");
          mod.add(n);
          skipTo(it, NodeType.END, "SP");
          break;
        case "SC":
          break;
        case "SIG":
          skipTo(it, NodeType.END, "SIG");
          break;
        case "TLN":
          n = new EmptyNode(node);
          ((TagNode) n).setName("L");
          ((TagNode) n).setAttribute("n", "");
          mod.add(n);
          mod.add(node);
          break;
        case "#TEXT":
          if (inHW) {
            n = new TextNode(node);
            n.setText(node.getText().replaceFirst("[(]", ""));
            inHW = false;
            mod.add(n);
            break;
          }
          mod.add(node);
          break;
        default:
          mod.add(node);
      }
    }
    return mod;

  }

}
