/*
 * Copyright (C) 2015 Michael Joyce <ubermichael@gmail.com>
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

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class CodePoint {

  /**
   * Short name, eg. apos, lt, nbsp.
   */
  private final String name;

  /**
   * Character value as a number.
   */
  private final int value;

  public CodePoint(String name, int value) {
	this.name = name;
	this.value = value;
  }

  /**
   * @return the name
   */
  public String getName() {
	return name;
  }

  /**
   * @return the value
   */
  public String getValue() {
	return new String(Character.toChars(value));
  }
  
  public int dec() {
	return value;
  }
  
  public String hex() {
	return String.format("%04x", value);
  }
  
  public String unicodePoint() {
	return String.format("U+%s", hex());
  }
  
  public String description() {
	return Character.getName(value);
  }
  
  public String toString() {
	return String.format("%12s %2s %4d %4s %6s", name, getValue(), dec(), hex(), unicodePoint());
  }
  
}
