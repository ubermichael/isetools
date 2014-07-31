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
import java.io.File;

/**
 *
 * @author michael
 */
public class Tester {

  public static void main(String[] args) {
    Log log = Log.getInstance();
    try {
      DOM dom = new DOMBuilder(new File("Oth_M.txt")).build();
      Annotation notes = Annotation.builder().from(new File("Oth_M_annotation.xml")).build();
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
