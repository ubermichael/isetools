/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.node.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Tokenizer {

  private Scanner scanner;

  //  @todo make this a string buffer.
  private String current_text = "";

  private AbstractNode current_node;

  private String current_attribute_name;
  private String current_attribute_value;
  private String current_attribute_delimiter;

  private String state = "data";

  public Tokenizer(String s) {
    this.scanner = new Scanner(s);
  }

  public Tokenizer(File f) throws FileNotFoundException {
    this.scanner = new Scanner(f);
  }

  private char getc() throws IOException {
    char c = this.scanner.getc();
    this.current_text += c;
    return c;
  }

  private void ungetc() throws IOException {
    current_text = current_text.substring(0, current_text.length() - 1);
    this.scanner.ungetc();
  }

  public AbstractNode getNode() {
    AbstractNode n = null;
    Method m = null;
    Class<?> c = this.getClass();
    while (n == null && !scanner.finished()) {

      try {
        m = c.getDeclaredMethod("state_" + this.state);
      } catch (Exception ex) {
        Logger.getLogger(Tokenizer.class.getName()).log(Level.SEVERE, null, ex);
      }

      try {
        n = (AbstractNode) m.invoke(this);
      } catch (Exception ex) {
        Logger.getLogger(Tokenizer.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return n;
  }

  private void prepare_node(String type) throws Exception {
    if (current_node != null) {
      throw new Exception("Must finish_node() before calling prepare_node().");
    }
    switch (type) {
      case "text":
        current_node = new TextNode();
        break;
      case "abbr":
        current_node = new AbbrNode();
        break;
      default:
        throw new Exception("Unknown node type: " + type);
    }
    current_node.setLine(scanner.line());
    current_node.setSource(scanner.source());
  }

  private AbstractNode finish_node() {
    AbstractNode n = current_node;
    current_node = null;
    n.setText(current_text);
    current_text = "";
    return n;
  }

  private AbstractNode state_data() throws IOException, Exception {

    while ("data".equals(state) && !scanner.finished()) {
      char c = this.getc();
      switch (c) {
        case '<':
          break;
        case '>':
          break;
        case '{':
          break;
        case '|':
          state = "abbr";
        case (char) -1:
          break;
        default:
          state = "text";
      }
    }
    return null;
  }

  private AbstractNode state_text() throws Exception {
    prepare_node("text");
    while ("text".equals(state) && !scanner.finished()) {
      char c = this.getc();
      if ("<>{}|".indexOf(c) != -1) {
        this.ungetc();
        state = "data";
      }
    }
    return finish_node();
  }

  private AbstractNode state_abbr() throws IOException, Exception {
    prepare_node("abbr");
    while ("abbr".equals(state) && !scanner.finished()) {
      char c = this.getc();
      if (c == '|') {
        state = "data";
        return this.finish_node();
      }
    }
    return finish_node();
  }
}
