/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise.dom;

import ca.nines.ise.node.Node;
import ca.nines.ise.node.TagNode;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;

/**
 * An ISE DOM (a document object model) is a stream of Node objects. Since
 * ISE tags are not hierarchical, they cannot be stored as a tree. DOM
 * extends {@code ArrayList<Node>} to do most of the heavy lifting.
 * <p>
 * DOM also stores all of the text used to create the DOM, so it can 
 * be accessed later.
 *
 * @see Node
 * @see ArrayList
 * 
 * @author Michael Joyce <michael@negativespace.net>
 */
public class DOM extends ArrayList<Node> {

  private String source;
  private HashMap<String, Node> index = null;
  private String[] lines;

  /**
   * Either "#STRING" if the DOM was created by parsing a string, or the 
   * absolute path to the file parsed to create the DOM.
   * 
   * @return  The source of the data in the DOM.
   */
  public String getSource() {
    return source;
  }

  /**
   * Set the source for the DOM, either "#STRING" or the absolute
   * file path.
   * 
   * @param source 
   */
  protected void setSource(String source) {
    this.source = source;
  }
  
  /**
   * Calculate an internal index for the DOM to make some lookups 
   * faster. Also sets the TLN and ASL properties of each node
   * in the DOM.
   * <p>
   * If you change the DOM by adding or removing nodes, or if you 
   * change the act, scene, line, or TLN numbering in the DOM
   * you should call this function.
   */
  public void index() {
    Iterator<Node> i = this.iterator();
    index = new HashMap<>();
    String act = "0";
    String scene = "0";
    String line = "0";
    String tln = "0";
    
    while(i.hasNext()) {
      Node n = i.next();
      switch(n.getName()) {
        case "ACT":
          act = ((TagNode)n).getAttribute("n");
          break;
        case "SCENE":
          scene = ((TagNode)n).getAttribute("n");
          break;
        case "L":
          line = ((TagNode)n).getAttribute("n");
          break;
        case "TLN":
          tln = ((TagNode)n).getAttribute("n");
          index.put(tln, n);
          break;
      }
      n.setTln(tln);
      n.setAsl(act + "." + scene + "." + line);
    }
  }
  
  /**
   * Store the lines of text used to create the DOM.
   * 
   * @param lines  The data used to create the DOM.
   */
  public void setLines(String lines) {
    this.lines = lines.split("\n");
  }
  
  /**
   * Store the text used to create the DOM.
   * 
   * @param lines  The data used to create the DOM.
   */
  public void setLines(String[] lines) {
    this.lines = lines;
  }

  /**
   * Get the nth line of text which was parsed to create the DOM. Note that
   * the lines are stored in a zero-based array. Line numbers in nodes are 
   * one-based. Do your arithmetic carefully.
   * 
   * @param n  The index of the line to get.
   * @return  The nth line (zero based).
   */
  public String getLine(int n) {
    if(lines == null) {
      return "";
    }
    if(n >= lines.length) {
      return "";
    }
    if(n < 0) {
      return "";
    }
    
    return lines[n];
  }
  
  /**
   * Produce a string representation of the DOM by stringifying all of
   * the nodes in the DOM. 
   * <p>
   * For serialization into SGML, text, or XML see elsewhere.
   * 
   * @todo figure out where the elsewhere is.
   * 
   * @return  a string representation.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb);

    Iterator<Node> i = this.iterator();

    while(i.hasNext()) {
      Node n = i.next();
      formatter.format("%s:%s%n", this.getSource(), n);
    }

    return sb.toString();
  }

  /**
   * Create an return an iterator for the DOM.
   * 
   * @return an return an iterator for the DOM. 
   */
  @Override
  public Iterator<Node> iterator() {
    return this.iterator();
  }
  
  
}
