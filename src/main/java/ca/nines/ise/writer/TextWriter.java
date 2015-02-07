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
package ca.nines.ise.writer;

import ca.nines.ise.document.Annotation;
import ca.nines.ise.dom.DOM;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * TextWriter creates a text representation of a DOM.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class TextWriter extends Writer {

  /**
   * Construct a text writer and send output to System.out.
   *
   * @throws UnsupportedEncodingException
   * @throws ParserConfigurationException
   */
  public TextWriter() throws UnsupportedEncodingException, ParserConfigurationException {
    this(System.out);
  }

  /**
   * Construct a text writer and send output to a print stream.
   *
   * @param out the output destination
   *
   * @throws UnsupportedEncodingException
   * @throws ParserConfigurationException
   */
  public TextWriter(PrintStream out) throws UnsupportedEncodingException, ParserConfigurationException {
    super(out);
  }

  /**
   * Render the DOM into text.
   *
   * @param dom
   */
  @Override
  public void render(DOM dom) {
    try {
      out.print(dom.unicode());
    } catch (IOException ex) {
      Logger.getLogger(TextWriter.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Unsupported.
   *
   * @param dom the DOM to render
   * @param ann annotations
   *
   * @throws TransformerConfigurationException
   * @throws TransformerException
   * @throws IOException
   * @throws Exception
   */
  @Override
  public void render(DOM dom, Annotation ann) throws TransformerConfigurationException, TransformerException, IOException, Exception {
    throw new UnsupportedOperationException("Not supported."); //To change body of generated methods, choose Tools | Templates.
  }

}
