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

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class DOM extends ArrayList<Node> {

  private String source;
  private HashMap<String, Node> index = null;
  private String[] lines;

  public String getSource() {
    return source;
  }

  protected void setSource(String source) {
    this.source = source;
  }
  
  public void index() {
    DOMIterator i = this.getIterator();
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
  
  public void setLines(String lines) {
    this.lines = lines.split("\n");
  }
  
  public void setLines(String[] lines) {
    this.lines = lines;
  }
  
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
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb);

    DOMIterator i = this.getIterator();

    while(i.hasNext()) {
      Node n = i.next();
      formatter.format("%s:%s%n", this.getSource(), n);
    }

    return sb.toString();
  }

  public DOMIterator getIterator() {
    return new DOMIterator(this);
  }

}
