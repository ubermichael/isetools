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

package ca.nines.ise.util;

import java.util.Formatter;

/**
 * Location data to add to an XML node. 
 * 
 * All location data is added during the parsing process in a user data
 * attribute for each node.
 *
 * see http://javacoalface.blogspot.ca/2011/04/line-and-column-numbers-in-xml-dom.html
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class LocationData {

  /**
   * The key used to store the data in the user data.
   */
  public static final String LOCATION_DATA_KEY = "locationDataKey";

  /**
   * The XML systemId that generated the source. Usually the file name.
   */
  private final String systemId;

  /**
   * The line number of the start tag.
   */
  private final int startLine;
  
  /**
   * The column of the start tag.
   */
  private final int startColumn;
  
  /**
   * The line of the end tag.
   */
  private final int endLine;
  
  /**
   * The column of the end tag,
   */
  private final int endColumn;

  public LocationData(String systemId, int startLine, int startColumn, int endLine, int endColumn) {
    super();
    this.systemId = systemId;
    this.startLine = startLine;
    this.startColumn = startColumn;
    this.endLine = endLine;
    this.endColumn = endColumn;
  }

  /**
   * The systemID (usually a file path) for the XML data.
   * 
   * @return a description of the source of the XML data
   */
  public String getSystemId() {
    return systemId;
  }

  /**
   * Gets line number of the start tag
   * 
   * @return line number
   */
  public int getStartLine() {
    return startLine;
  }

  /**
   * Gets the column number of the start tag
   * 
   * @return the column number
   */
  public int getStartColumn() {
    return startColumn;
  }

  /**
   * Gets the line number of the end tag
   * 
   * @return end line number
   */
  public int getEndLine() {
    return endLine;
  }

  /**
   * Gets the column number of the end tag
   * 
   * @return end column number
   */
  public int getEndColumn() {
    return endColumn;
  }

  /**
   * Stringifies the location data. Really only useful for debugging.
   * 
   * @return a mostly-useless string.
   */
  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    formatter.format("%s:%s:%s", systemId, startLine, startColumn);
    return formatter.toString();
  }
}
