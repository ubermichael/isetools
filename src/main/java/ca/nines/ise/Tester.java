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
package ca.nines.ise;

import ca.nines.ise.document.Annotation;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.log.Log;
import ca.nines.ise.writer.RTFWriter;
import ca.nines.ise.writer.Writer;
import java.io.IOException;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 * Dumb main class to test out experimental stuff.
 * <p>
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Tester {

	private static final Logger logger = Logger.getLogger(Tester.class.getName());

	/**
	 * Experiment runner.
	 * <p>
	 * @param args command line arguments.
	 */
	public static void main(String[] args) throws Exception {
		Log log = Log.getInstance();
		String s = "<MODE t=\"verse\">\n"
			+ "<L n=\"1\" /><TLN n=\"3\" /><S><SP norm=\"Roderigo\">Roderigo</SP>\n"
			+ "<L n=\"2\" /><TLN n=\"4\" />Tush, never tell me! I take it much unkindly.\n"
			+ "</MODE>";

		String t = "<annotations>"
			+ "<note>\n"
			+ "<ln tln=\"4\"/>\n"
			+ "<lem>tell me</lem>\n"
			+ "<level n=\"1\">\n"
			+ "Exclamation of impatience.\n"
			+ "</level>\n"
			+ "</note>"
			+ "<note>\n"
			+ "<ln tln=\"4\"/>\n"
			+ "<lem>unkindly</lem>\n"
			+ "<level n=\"1\">\n"
			+ "Exclamation of impatience.\n"
			+ "</level>\n"
			+ "</note>"
			+ "</annotations>";

		DOM d = new DOMBuilder(s).build();
		Annotation a = Annotation.builder().from(t).build();

		Writer rtf = new RTFWriter();
		rtf.render(d, a);
		
		if (log.count() > 0) {
			System.err.println("");
			System.err.println(log);
		}
	}
}
