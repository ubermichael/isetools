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
