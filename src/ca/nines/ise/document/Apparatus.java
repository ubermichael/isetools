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
 *
 * @todo should this class just extend ArrayList<T> ?
 * <p>
 * @author michael
 * @param <T>
 */
abstract public class Apparatus<T extends Lemma> implements Iterable<T> {

  private final List<T> lemmas;
  private final String source;
  private final Map<String, List<T>> index;

  public abstract static class ApparatusBuilder<T extends Lemma> {

    protected List<T> lemmas;
    protected String source;

    public ApparatusBuilder() {
      lemmas = new ArrayList<>();
      source = "";
    }

    public void addLemma(T t) {
      lemmas.add(t);
    }

    public void setSource(String source) {
      this.source = source;
    }
  }

  private void addToIndex(String tln, T lemma) {
    if (index.get(tln) == null) {
      index.put(tln, new ArrayList<T>());
    }
    index.get(tln).add(lemma);
  }

  protected Apparatus(String source, List<T> lemmas) {
    this.source = source;
    this.lemmas = new ArrayList<>(lemmas);
    this.index = new HashMap<>();

    for (T lemma : lemmas) {
      if (lemma.isTlnSplit()) {
        addToIndex(lemma.getTlnStart(), lemma);
        addToIndex(lemma.getTlnEnd(), lemma);        
      } else {
        addToIndex(lemma.getTln(), lemma);        
      }
    }
  }

  public T get(int i) {
    return lemmas.get(i);
  }
  
  public List<T> get(String tln) {
    return new ArrayList<>(index.get(tln));
  }

  /**
   * @return the source
   */
  public String getSource() {
    return source;
  }

  @Override
  public Iterator<T> iterator() {
    return lemmas.iterator();
  }

  public int size() {
    return lemmas.size();
  }

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
