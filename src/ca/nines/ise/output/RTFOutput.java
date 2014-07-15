/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.output;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfParagraphStyle;
import com.lowagie.text.rtf.text.RtfTab;
import java.awt.Color;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import javax.xml.parsers.ParserConfigurationException;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

  private void startParagraph() throws DocumentException {
    if (!p.isEmpty() && !StringUtils.isWhitespace(p.getContent())) {
      doc.add(p);
    }
    p = new Paragraph();
  }

  private void finishParagraph(RtfParagraphStyle style) throws DocumentException {
    if (!p.isEmpty() && !StringUtils.isWhitespace(p.getContent())) {
      doc.add(p);
    }
  }

  private void addChunk(String txt) {
    if (txt.length() > 0) {
      p.add(new Chunk(txt, fontStack.getFirst()));
    }
  }

  @Override
  public void render(DOM dom) throws DocumentException, IOException {

    dom.index();
    fontStack = new ArrayDeque<>();
    fontStack.push(FontFactory.getFont("Times New Roman", 12, Color.BLACK));
    Font font;

    boolean inSP = false;
    boolean inSD = false;
    boolean inDQ = false; // in a double quote
    boolean inSQ = false; // in a single quote
    char part = 'i';

    Pattern squareBraces = Pattern.compile("([^\\[]*)\\[([^\\]]*)\\](.*)");

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
              EmptyNode en = (EmptyNode) n;
              if (en.hasAttribute("part")) {
                part = en.getAttribute("part").charAt(0);
              } else {
                part = 'i';
              }
              break;
          }
          break;
        case END:
          switch (n.getName()) {
            case "FOREIGN":
              fontStack.pop();
              break;
            case "LD":
              startParagraph();
              fontStack.pop();
              break;
            case "SD":
              fontStack.pop();
              inSD = false;
              break;
            case "SP":
              inSP = false;
              switch (part) {
                case 'i':
                  break;
                case 'm':
                  RtfTab mtab = new RtfTab(100, RtfTab.TAB_LEFT_ALIGN);
                  p.add(mtab);
                  addChunk("\t");
                  break;
                case 'f':
                  RtfTab ftab = new RtfTab(200, RtfTab.TAB_LEFT_ALIGN);
                  p.add(ftab);
                  addChunk("\t\t");
                  break;
              }
              break;
          }
          break;
        case START:
          switch (n.getName()) {
            case "FOREIGN":
              font = new Font(fontStack.getFirst());
              font.setStyle(Font.ITALIC);
              fontStack.push(font);
              break;
            case "LD":
              startParagraph();
              break;
            case "SD":
              font = new Font(fontStack.getFirst());
              font.setStyle(Font.ITALIC);
              StartNode start = (StartNode) n;
              if (start.hasAttribute("t") && start.getAttribute("t").contains("exit")) {
                p.setAlignment(Element.ALIGN_RIGHT);
              }
              if (start.hasAttribute("t") && start.getAttribute("t").contains("optional")) {
                font.setColor(Color.GRAY);
              }
              fontStack.push(font);
              inSD = true;
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
          String txt = n.getText();
          txt = txt.replace("--", "\u2014");
          txt = txt.replace("\n", "");

          if (inSP) {
            addChunk(txt.toUpperCase() + ". ");
            break;
          }

          if (inSD) {
            Matcher m = squareBraces.matcher(txt);

            if (m.matches()) {
              font = new Font(fontStack.getFirst());
              font.setStyle(Font.NORMAL);

              StringBuilder sb = new StringBuilder();
              for (int i = 0; i < txt.length(); i++) {
                char c = txt.charAt(i);
                if (c == '[' || c == ']') {
                  if (sb.length() > 0) {
                    addChunk(sb.toString());
                    sb = new StringBuilder();
                  }
                  fontStack.push(font);
                  addChunk(String.valueOf(c));
                  fontStack.pop();
                } else {
                  sb.append(c);
                }
              }
              if (sb.length() > 0) {
                addChunk(sb.toString());
              }
              break;
            }
          }

          // fix quotation marks.
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < txt.length(); i++) {
            char c = txt.charAt(i);
            switch (c) {
              case '"':
                if (inDQ) {
                  // typographer's end quote.
                  sb.append("\u201D");
                  inDQ = false;
                  inSQ = false;
                } else {
                  // typographer's start quote.
                  sb.append("\u201C");
                  inDQ = true;
                  inSQ = false;
                }
                break;
              case '\'':
                sb.append("\u2019");
                break;
              default:
                sb.append(c);
            }
          }
          addChunk(sb.toString());
          break;
      }
    }

    startParagraph();

    doc.close();
  }

}
