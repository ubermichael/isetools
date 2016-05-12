/*
 * Copyright (C) 2016 Malcolm Moran <malcolm.moran@outlook.com>
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

import java.util.LinkedList;

/**
 * Stack to be used in validators to store nodes as they loop through a given DOM.
 * ValidatorStack provides functions to compare and modify nodes in the stack via
 * their names (.getName()).
 * 
 * @author Malcolm Moran <malcolm.moran@outlook.com>
 */

import ca.nines.ise.node.Node;

public class ValidatorStack<E extends Node> extends LinkedList<E>{
  
  /**
   * returns the head of the nodeStack
   * null if empty
   */
  public E get_head(){
    if (!this.isEmpty())
      return this.peekFirst();
    return null;
  }
  
  /**
   * returns the  name of the head of nodeStack
   * null if empty
   */
  public String get_head_name(){
    if (!this.isEmpty())
      return this.peekFirst().getName();
    return null;
  }
  
  /**
   * true if the given name is equal to the name at the head of nodeStack
   * false if stack is empty or head is not equal
   */
  public Boolean is_head_equal(String name){
    return get_head_name().toLowerCase().equals(name.toLowerCase());
  }
  
  /**
   * true if node at @i is equal to given name
   * false otherwise
   */
  public Boolean is_equal(int i, String name){
    if (this.get(i) != null)
      return this.get(i).getName().toLowerCase().equals(name.toLowerCase());
    return null;
  }
  
  /**
   * returns the first instance of @n's name in nodeStack
   * null if no occurence
   */
  public E get_first(Node n){
    for (E s : this){
      if (s.getName().toLowerCase().equals(n.getName().toLowerCase()))
        return s;
    }
    return null;
  }

  /**
   * returns the first instance of s in nodeStack
   * null if no occurence
   */
  public E get_first(String s){
    for (E n : this){
      if (n.getName().toLowerCase().equals(s.toLowerCase()))
        return n;
    }
    return null;
  }
  
  /**
   * returns true if name exists in the list
   * false otherwise
   * @param name
   */
  public Boolean in_tag(String name){
    return get_first(name) != null;
  }
  
/**
 * removes the first occurence of a node with the same name as @n
 */
  public void remove_first(Node n){
    Node end = this.get_first(n);
    if (end != null)
      this.remove(end);
  }
}
