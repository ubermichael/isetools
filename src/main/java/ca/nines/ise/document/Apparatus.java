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
package ca.nines.ise.document;

import ca.nines.ise.node.lemma.Lemma;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Common functionality for Annotation and Collation collections.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 * @param <T>
 */
abstract public class Apparatus<T extends Lemma> implements Iterable<T> {

  /**
   * List of notes or colls.
   */
  private final List<T> lemmas;

  /**
   * Source of the data
   */
  private final String source;

  /**
   * Index mapping TLNs to lemmas.
   */
  private final Map<String, List<T>> tlnIndex;

  /**
   * Maps IDs to lemmas. IDs are used in the RTF transformer.
   */
  private final Map<Long, T> idIndex;

  /**
   * Builder for creating Apparatus objects.
   *
   * @param <T>
   */
  public abstract static class ApparatusBuilder<T extends Lemma> {

    /**
     * List of lemmas.
     */
    protected List<T> lemmas;

    /**
     * Source of the data.
     */
    protected String source;

    /**
     * Constructor.
     */
    public ApparatusBuilder() {
      lemmas = new ArrayList<>();
      source = "";
    }

    /**
     * Add a lemma to the list.
     *
     * @param t
     */
    public void addLemma(T t) {
      lemmas.add(t);
    }

    /**
     * Set the source of the lemmas.
     *
     * @param source
     */
    public void setSource(String source) {
      this.source = source;
    }
  }

  /**
   * Add a lemma to the TLN index.
   *
   * @param tln
   * @param lemma
   */
  private void addToIndex(String tln, T lemma) {
    if (tlnIndex.get(tln) == null) {
      tlnIndex.put(tln, new ArrayList<T>());
    }
    tlnIndex.get(tln).add(lemma);
  }

  /**
   * Protected constructor. Child classes should have private constructors.
   *
   * @param source
   * @param lemmas
   */
  protected Apparatus(String source, List<T> lemmas) {
    this.source = source;
    this.lemmas = new ArrayList<>(lemmas);
    this.tlnIndex = new HashMap<>();
    this.idIndex = new HashMap<>();

    for (T lemma : lemmas) {
      idIndex.put(lemma.getId(), lemma);
      if (lemma.isTlnSplit()) {
        addToIndex(lemma.getTlnStart(), lemma);
        addToIndex(lemma.getTlnEnd(), lemma);
      } else {
        addToIndex(lemma.getTln(), lemma);
      }
    }
  }

  /**
   * Return the i-th lemma.
   *
   * @param i
   * @return T
   */
  public T get(int i) {
    return lemmas.get(i);
  }

  /**
   * Get a list of lemmas for a TLN.
   *
   * @param tln
   * @return List
   */
  public List<T> get(String tln) {
    if (tlnIndex.containsKey(tln)) {
      return new ArrayList<>(tlnIndex.get(tln));
    } else {
      return null;
    }
  }

  /**
   * WTF is this?
   *
   * @param id
   * @return Lemma
   */
  public T find(long id) {
    return idIndex.get(id);
  }

  /**
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * Create and return an iterator for the lemmas.
   *
   * @return Iterator
   */
  @Override
  public Iterator<T> iterator() {
    return lemmas.iterator();
  }

  /**
   * Return the size of the lemma list.
   *
   * @return int
   */
  public int size() {
    return lemmas.size();
  }

  /**
   * Create and return a human readable string describing the lemmas.
   *
   * @return String
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Lemma lem : lemmas) {
      sb.append(lem.toString());
      sb.append("\n");
    }
    return sb.toString();
  }
}
