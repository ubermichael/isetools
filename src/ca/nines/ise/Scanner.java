/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.nines.ise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Scanner {

  private final String source;
  private final LineNumberReader br;
  private boolean finished = false;

  public Scanner(String s) {
    this.source = "#STRING";
    this.br = new LineNumberReader(new StringReader(s));
    br.setLineNumber(1);
  }

  public Scanner(File f) throws FileNotFoundException {
    this.source = f.getName();
    this.br = new LineNumberReader(new FileReader(f));
    br.setLineNumber(1);
  }

  public String source() {
    return this.source;
  }

  public int line() {
    return this.br.getLineNumber();
  }

  public boolean finished() {
    return this.finished;
  }

  public long skip(long n) throws IOException {
    return this.br.skip(n);
  }

  public char getc() throws IOException {
    this.br.mark(1);
    int i = this.br.read();
    char c = (char)i;
    if(i == -1) {
      this.finished = true;
    }
    return c;
  }

  public void ungetc() throws IOException {
    this.br.reset();
  }

  public char peek() throws IOException {
    char c;
    this.br.mark(1);
    c = (char)this.br.read();
    this.br.reset();
    return c;
  }

  public String peek(int n) throws IOException {
    char c[] = new char[n];
    this.br.mark(n);
    this.br.read(c, 0, n);
    this.br.reset();

    return new String(c);
  }

}
