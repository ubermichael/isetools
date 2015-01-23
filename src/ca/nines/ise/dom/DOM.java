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
package ca.nines.ise.dom;

import ca.nines.ise.node.Node;
import ca.nines.ise.node.TagNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * An ISE DOM (a document object model) is a stream of Node objects. Since ISE
 * tags are not hierarchical, they cannot be stored as a tree. DOM extends
 * {@code ArrayList<Node>} to do most of the heavy lifting.
 * <p>
 * DOM also stores all of the text used to create the DOM, so it can be accessed
 * later.
 */
public class DOM implements Iterable<Node> {

  // @todo make all of DOM's fields final, by moving
  // all the setters to DOMBuilder, just like Message and Message.MessageBuilder.
  private final Map<String, Node> index;
  private boolean reindex;
  private String[] lines;

  private String source;
  private DOMStatus status;

  protected final List<Node> nodes;

  public enum DOMStatus {

    CLEAN,
    WARNING,
    ERROR,
  }

  public DOM() {
    nodes = new ArrayList<>();
    index = new HashMap<>();
    reindex = true;
    status = DOMStatus.CLEAN;
  }

  public void add(Node n) {
    nodes.add(n);
  }

  public void addAll(DOM dom) {
    nodes.addAll(dom.nodes);
  }

  public DOM expanded() throws IOException {
    DOM dom = new DOM();
    for (Node n : nodes) {
      dom.addAll(n.expanded());
    }
    return dom;
  }

  public Node get(int i) {
    return nodes.get(i);
  }

  /**
   * Get the nth line of text which was parsed to create the DOM. Note that the
   * lines are stored in a zero-based array. Line numbers in nodes are
   * one-based. Do your arithmetic carefully.
   * <p>
   * @param n The index of the line to get.
   * <p>
   * @return The nth line (zero based).
   */
  public String getLine(int n) {
    if (lines == null) {
      return "";
    }
    if (n >= lines.length) {
      return "";
    }
    if (n < 0) {
      return "";
    }

    return lines[n];
  }

  public String[] getLines() {
    return lines;
  }

  /**
   * Either "#STRING" if the DOM was created by parsing a string, or the
   * absolute path to the file parsed to create the DOM.
   * <p>
   * @return The source of the data in the DOM.
   */
  public String getSource() {
    return source;
  }

  /**
   * @return DOMStatus
   */
  public DOMStatus getStatus() {
    return status;
  }

  public Node getTln(String tln) {
    if (needsReindex()) {
      index();
    }
    if (index.containsKey(tln)) {
      return index.get(tln);
    }
    for (Node n : nodes) {
      if (n instanceof TagNode && n.getName().toLowerCase().equals("tln")) {
        TagNode tn = (TagNode) n;
        if (tn.hasAttribute("n") && tn.getAttribute("n").equals(tln)) {
          return n;
        }
      }
    }
    return null;
  }

  /**
   * Gets a fragment of a DOM near a TLN. The fragment will include
   * {@code length - 1} tlns after the TLN.
   * <p>
   * Returns an empty fragment if the TLN doesn't exist.
   * <p>
   * @param tln
   * @param length <p>
   * @return a piece of the DOM
   */
  public Fragment getTlnFragment(String tln, int length) {
    Fragment fragment = new Fragment();
    if (needsReindex()) {
      index();
    }
    Node n = getTln(tln);
    int idx = nodes.indexOf(n);
    int found = 0;

    if (n != null) {
      for (int i = idx; i < nodes.size() && found <= length; i++) {
        Node t = nodes.get(i);
        if (found <= length) {
          fragment.add(t);
        }
        if (t.getName().toLowerCase().equals("tln")) {
          found++;
        }
      }
      fragment.nodes.remove(fragment.nodes.size() - 1);
    }
    return fragment;
  }
  
  public Node find_forward(Node node, String name) {
    index();
    for(int i = node.getPosition(); i < size(); i++) {
      if(nodes.get(i).getName().equals(name)) {
        return nodes.get(i);
      }
    }
    return null;
  }

  /**
   * Calculate an internal index for the DOM to make some lookups faster. Also
   * sets the position, TLN, and ASL properties of each node in the DOM.
   */
  public void index() {
    if (needsReindex()) {
      index.clear();
      String act = "0";
      String scene = "0";
      String line = "0";
      String tln = "0";
      TagNode tn;
      for (int i = 0; i < nodes.size(); i++) {
        Node n = nodes.get(i);
        if (n instanceof TagNode) {
          tn = (TagNode) n;
          switch (tn.getName()) {
            case "ACT":
              act = tn.getAttribute("n");
              break;
            case "SCENE":
              scene = tn.getAttribute("n");
              break;
            case "L":
              if (tn.hasAttribute("n")) {
                line = tn.getAttribute("n");
              }
              break;
            case "TLN":
              tln = tn.getAttribute("n");
              index.put(tln, tn);
              break;
          }
        }
        n.setPosition(i);
        n.setTLN(tln);
        n.setAsl(act + "." + scene + "." + line);
      }
    }
  }

  /**
   * @return the reindex
   */
  public boolean needsReindex() {
    return reindex;
  }

  /**
   * Create an return an iterator for the DOM.
   * <p>
   * @return an return an iterator for the DOM.
   */
  @Override
  public Iterator<Node> iterator() {
    return nodes.iterator();
  }

  public String plain() throws IOException {
    StringBuilder sb = new StringBuilder();
    for (Node n : nodes) {
      sb.append(n.plain());
    }
    return sb.toString();
  }

  /**
   * Store the lines of text used to create the DOM.
   * <p>
   * @param lines The data used to create the DOM.
   */
  public void setLines(String lines) {
    this.lines = lines.split("\n");
  }

  /**
   * Store the text used to create the DOM.
   * <p>
   * @param lines The data used to create the DOM.
   */
  public void setLines(String[] lines) {
    this.lines = lines;
  }

  /**
   *
   */
  public void requestReindex() {
    this.reindex = true;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(DOMStatus status) {
    if (status.compareTo(this.status) > 0) {
      this.status = status;
    }
  }

  public int size() {
    return nodes.size();
  }

  /**
   * Produce a string representation of the DOM by stringifying all of the nodes
   * in the DOM.
   * <p>
   * @return a string representation.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (Node n : nodes) {
      sb.append(n).append("\n");
    }
    return sb.toString();
  }

  public String unicode() throws IOException {
    StringBuilder sb = new StringBuilder();
    for (Node n : nodes) {
      sb.append(n.unicode());
    }

    return sb.toString();
  }

  /**
   * Set the source for the DOM, either "#STRING" or the absolute file path.
   * <p>
   * @param source
   */
  public void setSource(String source) {
    this.source = source;
  }

}
