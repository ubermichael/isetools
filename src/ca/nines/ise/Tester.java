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
package ca.nines.ise;

import ca.nines.ise.document.Annotation;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.log.Log;
import ca.nines.ise.writer.RTFWriter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Footnote;
import com.lowagie.text.Paragraph;
import com.lowagie.text.rtf.RtfWriter2;
import java.io.File;

/**
 *
 * @author michael
 */
public class Tester {

  public static void main(String[] args) {
    Log log = Log.getInstance();
    try {
//      Document doc = new Document();
//      RtfWriter2 writer = RtfWriter2.getInstance(doc, System.out);
//      doc.open();
//      
//      Paragraph p;
//      p = new Paragraph("hello world.");
//      p.add(new Chunk(" a hunk"));
//      p.add(new Footnote(" and a note to go with it."));
//      p.add(new com.lowagie.text.Annotation("dummy", "with some content."));
//      p.add(new Chunk("."));      
//      doc.add(p);
//      doc.add(new Footnote(" a second footnote."));
//
//      doc.close();
//      
      String oth = ""
              + "<L n=\"1\" /><TLN n=\"3\" /><S><SP norm=\"Roderigo\">Roderigo</SP>\n"
              + "<L n=\"2\" /><TLN n=\"4\" />Tush, never tell me! I take it much unkindly\n"
              + "<L n=\"3\" /><TLN n=\"5\" />That thou, Iago, who hast had my purse\n"
              + "<L n=\"4\" /><TLN n=\"6\" />As if the strings were thine, shouldst know of this.</S>\n"
              + "<L n=\"5\" /><TLN n=\"7\" /><S><SP norm=\"Iago\">Iago</SP>'Sblood, but you'll not hear me! If ever I did dream <TLN n=\"8\" />of such a matter, abhor me.</S>\n"
              + "<L n=\"8\" /><TLN n=\"9\" /><S><SP norm=\"Roderigo\">Roderigo</SP>Thou told'st me <TLN n=\"10\" />thou didst hold him in thy hate.</S>";
      String ann = ""
              + "<annotations><note>\n"
              + "<ln tln=\"4\"/>\n"
              + "<lem>Tush,</lem>\n"
              + "<level n=\"1\">\n"
              + "Exclamation of impatience.\n"
              + "</level>\n"
              + "</note>\n"
              + "<note>\n"
              + "<ln tln=\"6\"/>\n"
              + "<lem>this.</lem>\n"
              + "<level n=\"1\">\n"
              + "I.e. Othello's marriage to Desdemona.\n"
              + "</level>\n"
              + "</note>\n"
              + "<note>\n"
              + "<ln tln=\"7\"/>\n"
              + "<lem>'Sblood,</lem>\n"
              + "<level n=\"1\">\n"
              + "Contraction of \"God's blood,\" a common oath.\n"
              + "</level>\n"
              + "</note>\n"
              + "<note>\n"
              + "<ln tln=\"8\"/>\n"
              + "<lem>such a matter,</lem>\n"
              + "<level n=\"1\">\n"
              + "Another vague reference to Othello's marriage.\n"
              + "</level>\n"
              + "</note></annotations>";

      DOM dom = new DOMBuilder(oth).build();
      Annotation notes = Annotation.builder().from(ann).build();
      RTFWriter w = new RTFWriter();
      w.render(dom, notes);
    } catch (Exception ex) {
      ex.printStackTrace(System.err);
    } finally {
      if (log.count() > 0) {
        System.err.println("");
        System.err.println(log);
      }
    }
  }
}
