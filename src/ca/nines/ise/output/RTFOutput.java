/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.output;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.TextNode;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.rtf.RtfWriter2;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import javax.xml.parsers.ParserConfigurationException;

import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author michael
 */
public class RTFOutput extends Output {

  private final Document doc;
  private final RtfWriter2 writer;
  private ArrayDeque<Font> fontStack;
  private Paragraph p = new Paragraph();

  public RTFOutput() throws UnsupportedEncodingException, ParserConfigurationException {
    super();
    doc = new Document();
    writer = RtfWriter2.getInstance(doc, out);
  }

  public RTFOutput(PrintStream out) throws UnsupportedEncodingException, ParserConfigurationException {
    super(out);
    doc = new Document();
    writer = RtfWriter2.getInstance(doc, out);
  }

  private void skipTo(Iterator<Node> iterator, String tagName) {
    while (iterator.hasNext()) {
      Node n = iterator.next();
      if (n.getName().equals(tagName)) {
        return;
      }
    }
    return;
  }

  private void startParagraph() throws DocumentException {
    if (!p.isEmpty() && !StringUtils.isWhitespace(p.getContent())) {
      doc.add(p);
    }
    p = new Paragraph();
  }

  private void addChunk(String txt) {
    p.add(new Chunk(txt, fontStack.getFirst()));
  }

  @Override
  public void render(DOM dom) throws DocumentException, IOException {

    fontStack = new ArrayDeque<>();
    fontStack.push(FontFactory.getFont("Times New Roman", 12));
    Font font;

    boolean inSP = false;

    doc.open();
    startParagraph();

    Iterator<Node> iterator = dom.iterator();
    while (iterator.hasNext()) {
      Node n = iterator.next();
      switch (n.type()) {
        case CHAR:
          addChunk(n.unicode());
          break;
        case EMPTY:
          switch (n.getName()) {
            case "L":
              startParagraph();
              break;
          }
          break;
        case END:
          switch (n.getName()) {
            case "LD":
              startParagraph();
              fontStack.pop();
              break;
            case "SD":
              fontStack.pop();
            case "SP":
              addChunk(". ");
              inSP = false;
          }
          break;
        case START:
          switch (n.getName()) {
            case "LD":
              startParagraph();
              font = new Font(fontStack.getFirst());
              font.setStyle(Font.BOLD);
              fontStack.push(font);
              break;
            case "SD":
              font = new Font(fontStack.getFirst());
              font.setStyle(Font.ITALIC);
              fontStack.push(font);
              break;
            case "SP":
              inSP = true;
              break;
            case "S":
              startParagraph();
              break;
          }
          break;
        case TEXT:
          String txt = n.getText().trim();
          if (inSP) {
            txt = txt.toUpperCase();
          }
          if (!StringUtils.isWhitespace(txt)) {
            addChunk(txt);
          }
          break;
      }
    }
    startParagraph();
    doc.close();
  }

}
