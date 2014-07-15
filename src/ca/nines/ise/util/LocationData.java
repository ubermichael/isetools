/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.util;

import java.util.Formatter;

/**
 * Location data to add to an XML node.
 *
 * http://javacoalface.blogspot.ca/2011/04/line-and-column-numbers-in-xml-dom.html
 *
 * @author michael
 */
public class LocationData {

  public static final String LOCATION_DATA_KEY = "locationDataKey";

  private final String systemId;

  private final int startLine;
  private final int startColumn;
  private final int endLine;
  private final int endColumn;

  public LocationData(String systemId, int startLine, int startColumn, int endLine, int endColumn) {
    super();
    this.systemId = systemId;
    this.startLine = startLine;
    this.startColumn = startColumn;
    this.endLine = endLine;
    this.endColumn = endColumn;
  }

  public String getSystemId() {
    return systemId;
  }

  public int getStartLine() {
    return startLine;
  }

  public int getStartColumn() {
    return startColumn;
  }

  public int getEndLine() {
    return endLine;
  }

  public int getEndColumn() {
    return endColumn;
  }

  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    formatter.format("%s:%s:%s", systemId, startLine, startColumn);
    return formatter.toString();
  }
}
