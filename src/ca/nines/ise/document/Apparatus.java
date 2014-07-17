/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.document;

import ca.nines.ise.node.lemma.Lemma;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
  
  protected Apparatus(String source, List<T> lemmas) {
    this.source = source;
    this.lemmas = new ArrayList<>(lemmas);
  }
  
  public T get(int i) {
    return lemmas.get(i);
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
    Iterator<T> iterator = iterator();
    while(iterator.hasNext()) {
      sb.append(iterator.next().toString());
      sb.append("\n");
    }
    return sb.toString();
  }
}
