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
package ca.nines.ise.node.lemma;

import ca.nines.ise.util.LocationData;
import java.util.Formatter;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Node;

/**
 * Lemma is the base for annotations and collations. Lemmas are immutable
 * objects. Use LemmaBuilder to construct them.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
abstract public class Lemma {  
  long id;
  
  /**
   * The piece of text being annotated/collated
   */
  private final String lem;

  /**
   * The line number of the XML defining the lemma
   */
  private final int lineNumber;

  /**
   * the source of the lemma. The file path or "#STRING"
   */
  private final String source;

  /**
   * The TLN of the lemma. Either a single TLN or a range separated by a hyphen.
   */
  private final String tln;

  /**
   * The act/scene/line where of the location of the lemma in the document.
   */
  private final String asl;

  /**
   * If a lemma spans more than one TLN, it is separated by {@code . . . }.
   * Match it and return the parts.
   */
  private final static Pattern lemSplitterDots = Pattern.compile("(.*?) ?\\. \\. \\. ?(.*)");

  /**
   * If a lemma spans more than one line (which is different from spanning a
   * TLN) it is separated by {@code / }. Match it and return the parts.
   */
  private final static Pattern lemSplitterSlash = Pattern.compile("(.*?) ?/ ?(.*)");

  /**
   * If a lemma spans multiple TLNs, the TLN is a range separated by {@code - }.
   * Match it and return the parts.
   */
  private final static Pattern tlnSplitter = Pattern.compile("(\\p{Digit}+(?:\\.\\p{Digit}+)?) ?- ?(\\p{Digit}+(?:\\.\\p{Digit}+)?)");

  /**
   *
   */
  protected abstract static class LemmaBuilder {

    /**
     * The piece of text being annotated/collated
     */
    protected String lem;

    /**
     * The line number of the XML defining the lemma
     */
    protected int lineNumber;

    /**
     * the source of the lemma. The file path or "#STRING"
     */
    protected String source;

    /**
     * The TLN of the lemma. Either a single TLN or a range separated by a
     * hyphen.
     */
    protected String tln;

    /**
     * The act/scene/line where of the location of the lemma in the document.
     */
    protected String asl;
    
    /**
     * The ID of the lemma.
     */
    protected long id;
    
    /**
     * Generate thread-safe identifiers.
     */
    static final AtomicLong NEXT_ID = new AtomicLong(0);

    protected LemmaBuilder() {
      lem = "";
      lineNumber = 0;
      source = "";
      tln = "";
      asl = "";
    }

    /**
     * Construct a lemma from an XML node.
     *
     * @param n the Node from which to construct a lemma
     * @return the LemmaBuilder to enable method chaining.
     */
    public LemmaBuilder from(Node n) {
      LocationData loc = (LocationData) n.getUserData(LocationData.LOCATION_DATA_KEY);
      setSource(loc.getSystemId());
      setLineNumber(loc.getStartLine());

      return this;
    }

    /**
     * Set the notated text of the lemma.
     *
     * @param lem the lem to set
     * @return the LemmaBuilder to enable method chaining.
     */
    public LemmaBuilder setLem(String lem) {
      this.lem = lem;
      return this;
    }

    /**
     * Set the line number of the lemma being constructed.
     * 
     * @param lineNumber the lineNumber to set
     * @return the LemmaBuilder to enable method chaining.
     */
    public LemmaBuilder setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
      return this;
    }

    /**
     * Set the source of the lemma being constructed.
     * 
     * @param source the source to set
     * @return the LemmaBuilder to enable method chaining.
     */
    public LemmaBuilder setSource(String source) {
      this.source = source;
      return this;
    }

    /**
     * Set the TLN of the lemma being constructed.
     * 
     * @param tln the tln to set
     * @return the LemmaBuilder to enable method chaining.
     */
    public LemmaBuilder setTln(String tln) {
      this.tln = tln;
      return this;
    }

    /**
     * Set the act/scene/line of the lemma being constructed. ASLs should be 
     * of the form {@code 3.2.1} for Act 3, Scene 2, line 1.
     *
     * @param asl
     * @return the LemmaBuilder to enable method chaining.
     */
    public LemmaBuilder setAsl(String asl) {
      this.asl = asl;
      return this;
    }
  }

  /**
   * Construct a lemma object. Don't call this directly, use Lemma.builder() to 
   * get a builder object and call its .build() method.
   * 
   * @param lem
   * @param lineNumber
   * @param source
   * @param tln
   * @param asl 
   */
  protected Lemma(String lem, int lineNumber, String source, String tln, String asl, long id) {
    this.lem = lem;
    this.lineNumber = lineNumber;
    this.source = source;
    this.tln = tln;
    this.asl = asl;
    this.id = id;
  }

  /**
   * Get the annotated text of the lemma.
   * 
   * @return the lem
   */
  public String getLem() {
    return lem;
  }

  /**
   * Returns true if the lemma spans TLNs or lines.
   * 
   * @return true if the lemma is split.
   */
  public boolean isLemSplit() {
    Matcher dots = lemSplitterDots.matcher(lem);
    if (dots.matches()) {
      return true;
    }
    Matcher slash = lemSplitterSlash.matcher(lem);
    if (slash.matches()) {
      return true;
    }
    return false;
  }

  /**
   * If the lemma spans TLNs or lines, return the first part of the lemma.
   * 
   * @return the start of the lemma
   */
  public String getLemStart() {
    Matcher dots = lemSplitterDots.matcher(lem);
    if (dots.matches()) {
      return dots.group(1);
    }
    Matcher slash = lemSplitterSlash.matcher(lem);
    if (slash.matches()) {
      return slash.group(1);
    }
    return null;
  }

  /**
   * If the lemma spans TLNs or lines, return the last part of the lemma.
   * 
   * @return the end of the lemma
   */
  public String getLemEnd() {
    Matcher dots = lemSplitterDots.matcher(lem);
    if (dots.matches()) {
      return dots.group(2);
    }
    Matcher slash = lemSplitterSlash.matcher(lem);
    if (slash.matches()) {
      return slash.group(2);
    }
    return null;
  }

  /**
   * Fetch the line number where the lemma object is defined.
   * 
   * @return the lineNumber
   */
  public int getLineNumber() {
    return lineNumber;
  }

  /**
   * Fetch the source of the lemma
   * 
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * Get the TLN of the lemma
   * 
   * @return the tln
   */
  public String getTln() {
    return tln;
  }

  /**
   * If the lemma spans TLNs or lines, return true.
   * 
   * @return true if the lemma spans TLNs or lines.
   */
  public boolean isTlnSplit() {
    Matcher m = tlnSplitter.matcher(tln);
    return m.matches();
  }

  /**
   * If the lemma spans TLNs, return the first TLN.
   * 
   * @return the first TLN, or null if the lemma does not span TLNs
   */
  public String getTlnStart() {
    Matcher m = tlnSplitter.matcher(tln);
    if (m.matches()) {
      return m.group(1);
    }
    return null;
  }

  /**
   * If the lemma spans TLNs, return the end TLN.
   * 
   * @return the end TLN, or null if the lemma does not span TLNs
   */
  public String getTlnEnd() {
    Matcher m = tlnSplitter.matcher(tln);
    m.matches();
    if (m.matches()) {
      return m.group(2);
    }
    return null;
  }

  /**
   * Fetch the act/scene/line of the lemma in the document.
   * 
   * @return a string of the act/scene/line
   */
  public String getAsl() {
    return asl;
  }
  
  public long getId() {
    return id;
  }

  /**
   * Turn the lemma into a string. Mostly useful for debugging.
   * 
   * @return a string representation of the lemma.
   */
  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    return formatter.format("%s:%s @%s[%s] (%s)", source, lineNumber, tln, asl, lem).toString();
  }
}
