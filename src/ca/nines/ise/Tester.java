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
import ca.nines.ise.dom.Fragment;
import ca.nines.ise.log.Log;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.lemma.Note;

/**
 * Dumb main class to test out experimental stuff.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Tester {

  /**
   * Experiment runner.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
	Log log = Log.getInstance();
	try {
	  DOM dom = new DOMBuilder("Pretext<TLN n='1'/>Hello world.<TLN n='2'/>Hi there.<TLN n='3'>You look nice today.").build();
	  Annotation ann = Annotation.builder().from(""
			  + "<annotations>\n"
			  + "<note>\n"
			  + "<ln tln='1'/>\n"
			  + "<lem>world.</lem>\n"
			  + "<level n='2'>\n"
			  + ".\n"
			  + "</level>\n"
			  + "</note>\n"
			  + "<note>\n"
			  + "<ln tln='1'/>\n"
			  + "<lem>Hi</lem>\n"
			  + "<level n='1'>\n"
			  + "Exclamation of impatience.\n"
			  + "</level>\n"
			  + "</note>"	
			  + "<note>\n"
			  + "<ln tln='2'/>\n"
			  + "<lem>look nice</lem>\n"
			  + "<level n='2'>\n"
			  + "fancy.\n"
			  + "</level>\n"
			  + "</note>"
			  + "</annotations>").build();
	  Tester.preproces(dom, ann);
	  System.out.println(dom);	  
	  //System.out.println(ann);
	} catch (Exception ex) {
	  ex.printStackTrace(System.err);
	} finally {
	  if (log.count() > 0) {
		System.err.println("");
		System.err.println(log);
	  }
	}
  }

  private static void preproces(DOM dom, Annotation ann) {
	for(Note note : ann) {
	  Fragment frag = dom.getTlnFragment(note.getTln(), 2);	  
	  Node tln = dom.getTln(note.getTln());
	  // locate the footnote
	}
  }	
}
