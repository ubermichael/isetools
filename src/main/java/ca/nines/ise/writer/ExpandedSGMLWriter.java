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
import ca.nines.ise.node.Node;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * SGMLWriter serializes a document into the ISE variant of SGML.
 *
 * @author Maxwell Terpstra <terpstra@alumni.uvic.ca>
 */
public class ExpandedSGMLWriter extends Writer {

  /**
   * Construct a writer and send output to STDOUT.
   *
   * @throws ParserConfigurationException
   * @throws UnsupportedEncodingException
   */
  public ExpandedSGMLWriter() throws ParserConfigurationException, UnsupportedEncodingException {
    this(new PrintStream(System.out, true, "UTF-8"));
  }

  /**
   * Construct a writer and send output to the PrintStream.
   *
   * @param out output destination
   */
  public ExpandedSGMLWriter(PrintStream out) {
    super(out);
  }

  /**
   * Render the DOM into SGML with escapes expanded into unicode/markup.
   *
   * @param dom
   */
  @Override
  public void render(DOM dom) throws IOException {
    for (Node n : dom.expanded()) {
        out.print(n.sgml());
    }
  }

  /**
   * This is unsupported.
   *
   * @param dom
   * @param ann
   * @throws TransformerConfigurationException
   * @throws TransformerException
   * @throws IOException
   * @throws Exception
   */
  @Override
  public void render(DOM dom, Annotation ann) throws TransformerConfigurationException, TransformerException, IOException, Exception {
    throw new UnsupportedOperationException("Not supported.");
  }

}
