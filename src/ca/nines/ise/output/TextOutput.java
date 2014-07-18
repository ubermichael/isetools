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
import ca.nines.ise.log.Log;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author michael
 */
public class TextOutput extends Output {

  public TextOutput() throws UnsupportedEncodingException, ParserConfigurationException {
    super();
  }

  public TextOutput(PrintStream out) throws UnsupportedEncodingException, ParserConfigurationException {
    super(out);
  }

  @Override
  public void render(DOM dom) {
    try {
      out.print(dom.unicode());
    } catch (IOException ex) {
      Logger.getLogger(TextOutput.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
