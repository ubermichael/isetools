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
package ca.nines.ise.document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ISE Document class - metadata about a document.
 */
abstract public class Document {

  /**
   * Parser a file name into name and edition.
   */
  protected static final Pattern editionPattern
          = Pattern.compile("^(?<name>[a-zA-Z0-9]+)_(?<edition>[a-zA-Z0-9]+)\\.txt$");

  /**
   * Check the validity of a file name or path.
   *
   * @param filename file name to check.
   * @return boolean
   */
  public static final boolean validName(String filename) {
    Matcher m = editionPattern.matcher(filename);
    return m.matches();
  }

  /**
   * Extract the work code from the file name (Rom from Rom_F1.txt).
   *
   * @param filename name of an ISE document.
   * @return String
   */
  public static final String extractName(String filename) {
    Matcher m = editionPattern.matcher(filename);
    if (m.matches()) {
      return m.group("name");
    }
    if (m.find()) {
      return m.group("name");
    }
    return null;
  }

  /**
   * Extract the edition code from a file name.
   *
   * @param filename name of an ISE document.
   * @return String
   */
  public static final String extractEdition(String filename) {
    Matcher m = editionPattern.matcher(filename);
    if (m.matches()) {
      return m.group("edition");
    }
    if (m.find()) {
      return m.group("edition");
    }
    return null;
  }
}
