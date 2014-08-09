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
package ca.nines.ise.node.lemma;

import ca.nines.ise.node.lemma.LemmaTest.LemmaImpl.LemmaBuilderImpl;
import org.junit.Test;
import static org.junit.Assert.*;
import ca.nines.ise.util.BuilderInterface;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class LemmaTest {

  @Test
  public void testBuilderDefaults() {
    LemmaBuilderImpl b = new LemmaBuilderImpl();
    Lemma lemma = b.build();
    assertEquals("", lemma.getLem());
    assertFalse(lemma.isLemSplit());
    assertNull(lemma.getLemStart());
    assertNull(lemma.getLemEnd());
    assertEquals(0, lemma.getLineNumber());
    assertEquals("", lemma.getSource());
    assertEquals("", lemma.getTln());
    assertFalse(lemma.isTlnSplit());
    assertNull(lemma.getTlnStart());
    assertNull(lemma.getTlnEnd());
    assertEquals("", lemma.getAsl());

    assertTrue(true);
  }

  @Test
  public void testLemmaSplits() {
    LemmaBuilderImpl b;
    Lemma lemma;

    b = new LemmaBuilderImpl();
    b.setLem("abc . . . cde");
    lemma = b.build();
    assertTrue(lemma.isLemSplit());
    assertEquals("abc", lemma.getLemStart());
    assertEquals("cde", lemma.getLemEnd());

    b = new LemmaBuilderImpl();
    b.setLem("abc. . .cde");
    lemma = b.build();
    assertTrue(lemma.isLemSplit());
    assertEquals("abc", lemma.getLemStart());
    assertEquals("cde", lemma.getLemEnd());

    b = new LemmaBuilderImpl();
    b.setLem("abc / cde");
    lemma = b.build();
    assertTrue(lemma.isLemSplit());
    assertEquals("abc", lemma.getLemStart());
    assertEquals("cde", lemma.getLemEnd());

    b = new LemmaBuilderImpl();
    b.setLem("abc/cde");
    lemma = b.build();
    assertTrue(lemma.isLemSplit());
    assertEquals("abc", lemma.getLemStart());
    assertEquals("cde", lemma.getLemEnd());

    b = new LemmaBuilderImpl();
    b.setLem("abccde");
    lemma = b.build();
    assertFalse(lemma.isLemSplit());
    assertNull(lemma.getLemStart());
    assertNull(lemma.getLemEnd());
  }

  @Test
  public void testTlnSplit() {
    LemmaBuilderImpl b;
    Lemma lemma;

    b = new LemmaBuilderImpl();
    b.setTln("33-34");
    lemma = b.build();
    assertTrue(lemma.isTlnSplit());
    assertEquals("33", lemma.getTlnStart());
    assertEquals("34", lemma.getTlnEnd());

    b = new LemmaBuilderImpl();
    b.setTln("33 - 34");
    lemma = b.build();
    assertTrue(lemma.isTlnSplit());
    assertEquals("33", lemma.getTlnStart());
    assertEquals("34", lemma.getTlnEnd());

    b = new LemmaBuilderImpl();
    b.setTln("33.1 - 34.2");
    lemma = b.build();
    assertTrue(lemma.isTlnSplit());
    assertEquals("33.1", lemma.getTlnStart());
    assertEquals("34.2", lemma.getTlnEnd());

    b = new LemmaBuilderImpl();
    b.setTln("33.1");
    lemma = b.build();
    assertFalse(lemma.isTlnSplit());
    assertNull(lemma.getTlnStart());
    assertNull(lemma.getTlnEnd());
  }

  public static class LemmaImpl extends Lemma {

    protected static class LemmaBuilderImpl extends LemmaBuilder implements BuilderInterface<LemmaImpl> {

      @Override
      public LemmaImpl build() {
        return new LemmaImpl(lem, lineNumber, source, tln, asl, id);
      }
    }

    private LemmaImpl() {
      super("", 0, "", "", "", 0);
    }

    protected LemmaImpl(String lem, int lineNumber, String source, String tln, String asl, long id) {
      super(lem, lineNumber, source, tln, asl, id);
    }
  }

}
