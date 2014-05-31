/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Main {

  public static void main(String[] args) throws IOException {
    String in = "Hello\nworld.";
    Scanner s = new Scanner(in);
    char c = s.getc();
    while( ! s.finished()) {
      System.out.println(s.line() + ":" + c);
      c = s.getc();
    }
  }

}
