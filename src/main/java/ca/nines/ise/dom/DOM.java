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

import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.node.TextNode;
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

  /**
   * An index of TLNs => Nodes
   */
  private final Map<String, Node> index;

  /**
   * Does the DOM need to be reindexed.
   */
  private boolean reindex;

  /**
   * Lines of text from the source input.
   */
  private String[] lines;

  /**
   * The source of data for this DOM.
   */
  private String source;

  /**
   * The status of the DOM.
   */
  private DOMStatus status;

  /**
   * The Nodes in the DOM, in document order.
   */
  protected final List<Node> nodes;

  public enum DOMStatus {

    /**
     * The status of the document, indicating if the document parsed cleanly,
     * with warnings, or with errors.
     */
    CLEAN,
    WARNING,
    ERROR,
  }

  /**
   * Construct a new, empty DOM.
   */
  public DOM() {
    nodes = new ArrayList<>();
    index = new HashMap<>();
    reindex = true;
    status = DOMStatus.CLEAN;
  }
  
  /**
   * Append a node to the DOM.
   *
   * @param n
   */
  public void add(Node n) {
    nodes.add(n);
  }

  /**
   * Add all the nodes in a DOM to this DOM. Does not clone or copy the nodes.
   * They exist in both DOMs.
   *
   * TODO this is a seriously dumb thing to do. In particular it doesn't call
   * node.setOwner().
   *
   * @param dom
   */
  public void addAll(DOM dom) {
    nodes.addAll(dom.nodes);
  }

  /**
   * Remove a node from the dom.
   * 
   * @param n node to remove.
   */
    public void remove(EndNode n) {
        nodes.remove(n);
    }

  /**
   * Replace a node in the DOM stream.
   * 
   * @param n Node to be replaced
   * @param e Replacement node
   */
    public void replace(Node n, Node e) {
        int idx = nodes.indexOf(n);
        nodes.set(idx, e);
    }

  /**
   * Return an expanded DOM, where all Char and ABBR nodes are expanded into
   * fully tagged equivalents.
   *
   * @return expanded DOM
   * @throws IOException
   */
  public DOM expanded() throws IOException {
    DOM dom = new DOM();
    for (Node n : nodes) {
      dom.addAll(n.expanded());
    }
    return dom;
  }

  /**
   * Get the nth node in the DOM. The DOM starts counting nodes at zero.
   * @param i
   * @return The requested node or NULL.
   */
  public Node get(int i) {
	if(i >= nodes.size()) {
	  return null;
	}
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
  
  /**
   * Get the nodes for the nth line of input text. DOM lines are 1-based, not 
   * 0-based, so do your arithmetic carefully.
   * 
   * @param n
   * @return Node[]
   */
  public Fragment getLineFragment(int n) {
	  Fragment fragment = new Fragment();
      for(Node node : nodes) {
          if(node.getLine() == n){
              fragment.add(node);
          }
      }
      return fragment;
  }

  /**
   * Get all lines from the original source data.
   *
   * @return String[] of the lines in the source
   */
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
   * Get the status of the DOM,
   *
   * @return DOMStatus
   */
  public DOMStatus getStatus() {
    return status;
  }

  /**
   * Find the Node named TLN with attribute n equal to the parameter. Returns
   * null if the node does not exist.
   *
   * @param tln
   * @return Node or null
   */
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
   * @param length
   * <p>
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
        if (t.getName().toLowerCase().equals("tln")) {
          found++;
        }
        if (found <= length) {
          fragment.add(t);
        }
      }
    }
    return fragment;
  }

  /**
   * Start at node, and look forward in the DOM to find a node called name.
   *
   * @param node
   * @param name
   * @return Node or null
   */
  public Node findForward(Node node, String name) {
    index();
    for (int i = node.getPosition() + 1; i < size(); i++) {
      if (nodes.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
        return nodes.get(i);
      }
    }
    return null;
  }
  
  /**
   * Find all nodes in between first and last
   * 
   * @param first node
   * @param last node
   * @return list of nodes in between
   */
  public List<Node> get_between(Node first, Node last) {
    index();
    List<Node> found = new ArrayList<Node>();
    for (int i = first.getPosition() + 1; i < last.getPosition(); i++)
      found.add(nodes.get(i));
    return found;
  }
  
  /**
   * Get next node in the dom after @node
   * 
   * @param node
   * @return
   */
  public Node get_next(Node node){
    index();
    if (node.getPosition() < size())
      return get(node.getPosition()+1);
    else
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
      reindex = false;
    }
  }

  /**
   * @return returns true if the DOM should be reindexed.
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

  /**
   * Create a plain-text representation of a DOM, stripping out all markup.
   * @return
   * @throws IOException 
   */
  public String plain() throws IOException {
    StringBuilder sb = new StringBuilder();
    for (Node n : nodes) {
      sb.append(n.plain());
    }
    return sb.toString();
  }

  /**
   * Split text node oldText into new nodes at position p in oldText, 
   * and put newNode between them.
   *
   * @param oldText
   * @param newNode
   * @param pos
   */
  public void splitTextNode(TextNode oldText, int pos, Node newNode) {
    TextNode beforeNode = new TextNode(oldText);
    beforeNode.setText(oldText.getText().substring(0, pos));

    TextNode afterNode = new TextNode(oldText);
    afterNode.setText(oldText.getText().substring(pos));

    newNode.setOwner(this);

    nodes.remove(oldText.getPosition());
    nodes.add(oldText.getPosition(), afterNode);
    nodes.add(oldText.getPosition(), newNode);
    nodes.add(oldText.getPosition(), beforeNode);
    requestReindex(); // positions/indexes have changed.
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
   * Force the DOM to be reindexed the next time an index operation is
   * performed.
   */
  public void requestReindex() {
    this.reindex = true;
  }

  /**
   * Set the status of the DOM.
   *
   * @param status the status to set
   */
  public void setStatus(DOMStatus status) {
    if (status.compareTo(this.status) > 0) {
      this.status = status;
    }
  }

  /**
   * Return the size of the DOM - the number of nodes it contains.
   *
   * @return int
   */
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

  /**
   * Produce a string by concatenating the unicode version of each text, char,
   * and abbr node.
   *
   * @return String with only the the unicode representation of the DOM.
   * @throws IOException
   */
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
