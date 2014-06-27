/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.document;

import ca.nines.ise.node.lemma.Lemma;
import java.util.ArrayList;

/**
 *
 * @author michael
 * @param <T>
 */
abstract public class Apparatus<T extends Lemma> {

  ArrayList<T> lemmas;

}
