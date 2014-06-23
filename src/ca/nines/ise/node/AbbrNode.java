/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.node;

import ca.nines.ise.dom.Fragment;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class AbbrNode extends Node {

  @Override
  public NodeType type() {
    return NodeType.ABBR;
  }

  @Override
  public String getName() {
    return "#ABBR";
  }

  private Message error(String code) {
    Message m = Log.getInstance().error(code);
    m.setComponent(this.getClass().getCanonicalName());
    m.setSource(getSource());
    m.setColumnNumber(getColumn());
    m.setLineNumber(getLine());
    m.setTLN(getTln());
    return m;
  }

  @Override
  public Fragment expanded() {
    
    if(this._expanded != null) {
      return this._expanded;
    }
    
    String content = text.replaceAll("\\|", "");
    Pattern longAbbrPattern = Pattern.compile("^([a-zA-Z])\\^([a-zA-Z])$");
    Pattern shortAbbrPattern = Pattern.compile("^([a-zA-Z]+)$");
    Fragment fragment = new Fragment();
    StartNode start = new StartNode("ABBR");

    start.setAttribute("expan", "xxxxx");
    fragment.add(start);
    Matcher m = longAbbrPattern.matcher(content);
    if (m.matches()) {
      TextNode textNode = new TextNode();
      textNode.setText(m.group(1));
      fragment.add(textNode);
      fragment.add(new StartNode("SUP"));
      textNode = new TextNode();
      textNode.setText(m.group(2));
      fragment.add(textNode);
      fragment.add(new EndNode("SUP"));
    } else {
      if (!content.matches(shortAbbrPattern.pattern())) {
        Message msg = error("abbr.expand.syntax");
        msg.addNote("The syntax error occurs in " + text);
      }
      TextNode tn = new TextNode();
      tn.setText(content);
      fragment.add(tn);
    }
    fragment.add(new EndNode("ABBR"));

    this._expanded = fragment;
    
    return fragment;
  }

  @Override
  public String plain() {
    if(this._plain != null) {
      return this._plain;
    }
    this._plain = getText().replaceAll("[^a-zA-Z]*", "");
    return this._plain;
  }

  @Override
  public String unicode() {
    if(this._unicode != null) {
      return this._unicode;
    }
    this._unicode = getText().replaceAll("[^a-zA-Z]*", "");
    return this._unicode;
  }

}
