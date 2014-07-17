/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node.lemma;

import ca.nines.ise.util.LocationData;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Node;

/**
 *
 * @author michael
 */
abstract public class Lemma {

  private final String lem;
  private final int lineNumber;
  private final String node;
  private final String source;
  private final String tln;
  private final String asl;
  
  private final static Pattern lemSplitter = Pattern.compile("(.*) ?. . . ?(.*)");
  private final static Pattern tlnSplitter = Pattern.compile("([^-]*)-([^-]*)");
  
  public abstract static class LemmaBuilder {

    protected String lem;
    protected int lineNumber;
    protected String node;
    protected String source;
    protected String tln;
    protected String asl;

    protected LemmaBuilder() {
      lem = "";
      lineNumber = 0;
      node = "";
      source = "";
      tln = "";
      asl = "";
    }
    
    public LemmaBuilder from(Node n) { 
      LocationData loc = (LocationData) n.getUserData(LocationData.LOCATION_DATA_KEY);
      setSource(loc.getSystemId());
      setLineNumber(loc.getStartLine());

      return this;
    }

    /**
     * @param lem the lem to set
     */
    public LemmaBuilder setLem(String lem) {
      this.lem = lem;
      return this;
    }

    /**
     * @param lineNumber the lineNumber to set
     */
    public LemmaBuilder setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
      return this;
    }

    /**
     * @param node the node to set
     */
    public LemmaBuilder setNode(String node) {
      this.node = node;
      return this;
    }

    /**
     * @param source the source to set
     */
    public LemmaBuilder setSource(String source) {
      this.source = source;
      return this;
    }

    /**
     * @param tln the tln to set
     */
    public LemmaBuilder setTln(String tln) {
      this.tln = tln;
      return this;
    }
    
    public LemmaBuilder setAsl(String asl) {
      this.asl = asl;
      return this;
    }
  }

  protected Lemma(String lem, int lineNumber, String node, String source, String tln, String asl) {
    this.lem = lem;
    this.lineNumber = lineNumber;
    this.node = node;
    this.source = source;
    this.tln = tln;
    this.asl = asl;
  }

  /**
   * @return the lem
   */
  public String getLem() {
    return lem;
  }

  public boolean isLemSplit() {
    Matcher m = lemSplitter.matcher(lem);
    return m.matches();
  }
  
  public String getLemStart() {
    Matcher m = lemSplitter.matcher(lem);
    return m.group(1);
  }
  
  public String getLemEnd() {
    Matcher m = lemSplitter.matcher(lem);
    return m.group(2);
  }
  
  /**
   * @return the lineNumber
   */
  public int getLineNumber() {
    return lineNumber;
  }

  /**
   * @return the node
   */
  public String getNode() {
    return node;
  }

  /**
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * @return the tln
   */
  public String getTln() {
    return tln;
  }
  
  public boolean isTlnSplit() {
    Matcher m = tlnSplitter.matcher(tln);
    return m.matches();
  }
  
  public String getTlnStart() {
    Matcher m = tlnSplitter.matcher(tln);
    return m.group(1);
  }
  
  public String getTlnEnd() {
    Matcher m = tlnSplitter.matcher(tln);
    return m.group(2);
  }
  
  public String getAsl() {
    return asl;
  }

  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    return formatter.format("%s:%s @%s[%s] (%s)", source, lineNumber, tln, asl, lem).toString();
  }
}
