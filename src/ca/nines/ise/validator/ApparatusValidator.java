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
package ca.nines.ise.validator;

import ca.nines.ise.document.Apparatus;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.dom.Fragment;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.lemma.Lemma;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author michael
 * @param <T>
 */
abstract public class ApparatusValidator<T extends Apparatus> {
  private static final Pattern singleTlnPattern = Pattern.compile("\\p{Digit}+(?:\\.\\p{Digit}+)?");
  private static final Pattern splitTlnPattern = Pattern.compile("(\\p{Digit}+(?:\\.\\p{Digit}+)?) ?- ?(\\p{Digit}+(?:\\.\\p{Digit}+)?)");
  private static final Pattern splitLemPattern = Pattern.compile("(.*?) ?. . . ?(.*?)");

  public void validate(Lemma lem) {
    Matcher matcher;
    String cName = lem.getClass().getSimpleName().toLowerCase();

    matcher = singleTlnPattern.matcher(lem.getTln());
    if (matcher.matches()) {
      return;
    }

    matcher = splitTlnPattern.matcher(lem.getTln());
    if (matcher.matches()) {
      return;
    }

    Message m = Message.builder("validator." + cName + ".bad_tln")
            .fromLemma(lem)
            .addNote("The invalid TLN looks like " + lem.getTln())
            .build();
    Log.addMessage(m);
  }

  private void matchTlnLemma(DOM dom, String tln, String lemmaString, Lemma lemma, String cName) throws IOException {
    Message m;

    Fragment tlnFragment = dom.getTlnFragment(tln, 2);
    if (tlnFragment.size() == 0) {
      m = Message.builder("validator." + cName + ".tlnmissing")
              .fromLemma(lemma)
              .addNote("Could not find TLN " + tln + " in the document.")
              .build();
      Log.addMessage(m);
      return;
    }

    String tlnString = tlnFragment.unicode();
    tlnString = tlnString.replaceAll("\n", " ");
    tlnString = tlnString.replaceAll("  *", " ");
    lemmaString = new DOMBuilder(lemmaString).build().unicode();

    if (!tlnString.contains(lemmaString)) {
      m = Message.builder("validator." + cName + ".no_match")
              .fromLemma(lemma)
              .addNote("Lemma \"" + lemmaString + "\" did not match the document text \"" + tlnString + "\"")
              .build();
      Log.addMessage(m);
    }
  }

  public void validate(DOM dom, Lemma lemma) throws IOException {
    String cName = lemma.getClass().getSimpleName().toLowerCase();
    Message m;

    // most likely case.
    if (!lemma.isTlnSplit() && !lemma.isLemSplit()) {
      matchTlnLemma(dom, lemma.getTln(), lemma.getLem(), lemma, cName);
      return;
    }

    if (lemma.isTlnSplit() && !lemma.isLemSplit()) {
      matchTlnLemma(dom, lemma.getTlnStart(), lemma.getLem(), lemma, cName);
      matchTlnLemma(dom, lemma.getTlnEnd(), lemma.getLem(), lemma, cName);
      return;
    }

    if (!lemma.isTlnSplit() && lemma.isLemSplit()) {
      matchTlnLemma(dom, lemma.getTln(), lemma.getLemStart(), lemma, cName);
      matchTlnLemma(dom, lemma.getTln(), lemma.getLemEnd(), lemma, cName);
      return;
    }

    if (lemma.isTlnSplit() && lemma.isLemSplit()) {
      matchTlnLemma(dom, lemma.getTlnStart(), lemma.getLemStart(), lemma, cName);
      matchTlnLemma(dom, lemma.getTlnEnd(), lemma.getLemEnd(), lemma, cName);
      return;
    }

  }

}
