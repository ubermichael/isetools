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
package ca.nines.ise.document;

import ca.nines.ise.node.lemma.Lemma;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Apparatus collects the commonalities for Annotation and Collation objects. It
 * is a list of Lemma subclasses.
 *
 * @author michael
 * @param <T>
 */
abstract public class Apparatus<T extends Lemma> implements Iterable<T> {

  /**
   * A list of Lemma objects.
   */
  private final List<T> lemmas;

  /**
   * The path to the source of the lemmas.
   */
  private final String source;

  /**
   * An index of TLN to Lemmas
   */
  private final Map<String, List<T>> tlnIndex;

  /**
   * Lemmas have unique id attributes and this indexes them.
   */
  private final Map<Long, T> idIndex;

  /**
   * ApparatusBuilder constructs stuff, following the builder pattern.
   *
   * @param <T> The type to construct.
   */
  public abstract static class ApparatusBuilder<T extends Lemma> {

    /**
     * A list of lemma subclass objects.
     */
    protected List<T> lemmas;

    /**
     * The source of the lemmas.
     */
    protected String source;

    /**
     * Construct a new ApparatusBuilder object.
     */
    public ApparatusBuilder() {
      lemmas = new ArrayList<>();
      source = "";
    }

    /**
     * Add a new lemma to the list of lemmas being constructed.
     *
     * @param t the lemma to add
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
   * Index the lemma.
   *
   * @TODO is the TLN parameter necessary?
   *
   * @param tln The lemma's TLN
   *
   * @param lemma the lemma to index.
   */
  private void addToIndex(String tln, T lemma) {
    if (tlnIndex.get(tln) == null) {
      tlnIndex.put(tln, new ArrayList<T>());
    }
    tlnIndex.get(tln).add(lemma);
  }

  /**
   * Construct an Apparatus.
   *
   * @param source of the lemmas
   * @param lemmas lemmas in the apparatus
   */
  protected Apparatus(String source, List<T> lemmas) {
    this.source = source;
    this.lemmas = new ArrayList<>(lemmas);
    this.tlnIndex = new HashMap<>();
    this.idIndex = new HashMap<>();

    for (T lemma : lemmas) {
      idIndex.put(Long.valueOf(lemma.getId()), lemma);
      if (lemma.isTlnSplit()) {
        addToIndex(lemma.getTlnStart(), lemma);
        addToIndex(lemma.getTlnEnd(), lemma);
      } else {
        addToIndex(lemma.getTln(), lemma);
      }
    }
  }

  /**
   * Get the i-th lemma from the list
   *
   * @param i the index of the lemma to fetch
   *
   * @return T the lemma
   */
  public T get(int i) {
    return lemmas.get(i);
  }

  /**
   * Get the lemmas for a TLN
   *
   * @param tln lemmas to get
   *
   * @return List<T> list of lemmas with for the TLN
   */
  public List<T> get(String tln) {
    if (tlnIndex.containsKey(tln)) {
      return new ArrayList<>(tlnIndex.get(tln));
    } else {
      return null;
    }
  }

  /**
   * Fetch a single lemma with the given ID.
   *
   * @param id of the lemma to fetch
   *
   * @return lemma with the corresponding ID
   */
  public T find(long id) {
    return idIndex.get(Long.valueOf(id));
  }

  /**
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * Apparatus objects are iterable.
   *
   * @return Iterator
   */
  @Override
  public Iterator<T> iterator() {
    return lemmas.iterator();
  }

  /**
   * Count the lemmas in the apparatus.
   *
   * @return the size of the lemmas
   */
  public int size() {
    return lemmas.size();
  }

  /**
   * Generate a string representation of the Apparatus object. Mostly for
   * debugging.
   *
   * @return a string representation of the apparatus.
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
