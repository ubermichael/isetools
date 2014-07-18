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

package ca.nines.ise.output;

import ca.nines.ise.dom.DOM;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author michael
 */
abstract public class Output {

  protected final PrintStream out;

  // @TODO make this an interface by dropping out and the constructors.
  
  public Output() throws UnsupportedEncodingException, ParserConfigurationException {
    this(System.out);
  }

  public Output(PrintStream out) throws ParserConfigurationException, UnsupportedEncodingException {
    this.out = out;
  }

  abstract public void render(DOM dom) throws TransformerConfigurationException, TransformerException, IOException, Exception;

}
