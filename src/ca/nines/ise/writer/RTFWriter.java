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
package ca.nines.ise.writer;

import ca.nines.ise.document.Annotation;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.lemma.Note;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Footnote;
import com.lowagie.text.Paragraph;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfParagraphStyle;
import com.lowagie.text.rtf.text.RtfTab;
import java.awt.Color;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author michael
 */
public class RTFWriter extends Writer {

  private final Document doc;
  private final RtfWriter2 writer;
  private ArrayDeque<Font> fontStack;
  private Paragraph p = new Paragraph();

  private RtfParagraphStyle normal;
  private RtfParagraphStyle exit;
  private RtfParagraphStyle footnote;
  private RtfParagraphStyle ld;
  private RtfParagraphStyle p1;
  private RtfParagraphStyle p2;

  private String currentTln;
  private List<Note> lemmas = null;

  public RTFWriter() throws UnsupportedEncodingException, ParserConfigurationException {
    this(System.out);
  }

  public RTFWriter(PrintStream out) throws ParserConfigurationException, UnsupportedEncodingException {
    super(out);
    doc = new Document();
    writer = RtfWriter2.getInstance(doc, out);
    normal = new RtfParagraphStyle("ISE Normal", "Times New Roman", 12, Font.NORMAL, Color.BLACK);
    normal.setAlignment(Element.ALIGN_UNDEFINED);
    writer.getDocumentSettings().registerParagraphStyle(normal);

    ld = new RtfParagraphStyle("ISE h2", "ISE Normal");
    ld.setFontName("Helvetica");
    ld.setSize(16);
    ld.setStyle(Font.BOLD);
    writer.getDocumentSettings().registerParagraphStyle(ld);

    exit = new RtfParagraphStyle("ISE exit", "ISE Normal");
    exit.setAlignment(Element.ALIGN_RIGHT);
    exit.setStyle(Font.ITALIC);
    writer.getDocumentSettings().registerParagraphStyle(exit);

    p1 = new RtfParagraphStyle("ISE p1", "ISE Normal");
    p1.setFirstLineIndent(-19);
    p1.setIndentLeft(19);
    p1.setIndentRight(49);
    writer.getDocumentSettings().registerParagraphStyle(p1);

    p2 = new RtfParagraphStyle("ISE p2", "ISE Normal");
    p2.setFirstLineIndent(-19);
    p2.setIndentLeft(38);
    p2.setIndentRight(49);
    writer.getDocumentSettings().registerParagraphStyle(p2);

    footnote = new RtfParagraphStyle("ISE Footnote", "ISE Normal");
    footnote.setFirstLineIndent(-19);
    footnote.setIndentLeft(38);
    footnote.setIndentRight(49);
    footnote.setSize(10);
    writer.getDocumentSettings().registerParagraphStyle(footnote);
  }

  private void startParagraph() throws DocumentException, IOException {
    startParagraph(normal);
  }

  private void startParagraph(RtfParagraphStyle style) throws DocumentException, IOException {

    Paragraph tmp = new Paragraph("", p.getFont());
    Iterator iter = p.iterator();
    Map<Integer, Note> positions = new HashMap<>();

    while (iter.hasNext()) {
      Element e = (Element) iter.next();
      if (lemmas == null || !(e instanceof Chunk)) {
        tmp.add(e);
        continue;
      }

      Chunk c = (Chunk) e;
      String txt = c.getContent();
      boolean match = false;

      for (Note n : lemmas) {
        if (!n.hasNoteLevel("1")) {
          continue;
        }
        String lem;
        if (n.isLemSplit()) {
          lem = n.getLemEnd();
        } else {
          lem = n.getLem();
        }

        int lemPos = txt.indexOf(lem) + lem.length();
        if (lemPos >= 0) {
          positions.put(Integer.valueOf(lemPos), n);
          match = true;
        } else {
          if (!n.isLemSplit() || n.getTlnEnd().equals(currentTln)) {
            System.err.println("Cannot match lemma to document.");
            System.err.println(n);
          }
        }
      }

      if (match) {
        Integer[] ptns = positions.keySet().toArray(new Integer[positions.size()]);
        Arrays.sort(ptns);
        int p = 0, q = 0;
        for (Integer i : ptns) {
          p = i.intValue();
          try {
            tmp.add(new Chunk(txt.substring(q, p)));
            //Footnote ftn = new Footnote(new Chunk(positions.get(i).getNote("1").unicode().trim(), footnote));
            //tmp.add(ftn);
            tmp.add(new Chunk("*"));
            q = p;
          } catch (Exception exc) {
            System.err.println("Cannot position lemma in document at TLN " + currentTln);
            System.err.println(positions.get(i));
          }
        }
        if (p < txt.length()) {
          tmp.add(new Chunk(txt.substring(p)));
        }
      } else {
        tmp.add(c);
      }
      positions.clear();
    }

    if (!tmp.isEmpty() && !StringUtils.isWhitespace(tmp.getContent())) {
      doc.add(tmp);
    }
    p = new Paragraph("", style);
  }

  private void addChunk(String txt) {
    if (txt.length() > 0) {
      p.add(new Chunk(txt, fontStack.getFirst()));
    }
  }

  @Override
  public void render(DOM dom) throws DocumentException, IOException {
    render(dom, Annotation.builder().build());
  }

  public void render(DOM dom, Annotation annotation) throws DocumentException, IOException {

    dom.index();
    fontStack = new ArrayDeque<>();
    fontStack.push(FontFactory.getFont("Times New Roman", 12, Color.BLACK));
    Font font;

    boolean inSP = false; // in speech prefix
    boolean inSD = false; // in stage direction
    boolean inDQ = false; // in a double quote
    boolean inS = false; // in a speech
    boolean inHW = false;
    char part = 'i';

    Pattern squareBraces = Pattern.compile("([^\\[]*)\\[([^\\]]*)\\](.*)");

    doc.open();
    startParagraph();

    for (Node n : dom) {
      switch (n.type()) {
        case ABBR:
          addChunk(n.unicode());
          break;
        case CHAR:
          addChunk(n.unicode());
          break;
        case EMPTY:
          switch (n.getName()) {
            case "TLN":
              currentTln = ((EmptyNode) n).getAttribute("n");
              lemmas = annotation.get(currentTln);
              if (inS) {
                startParagraph(p2);
              } else {
                startParagraph(p1);
              }
              EmptyNode en1 = (EmptyNode) n;
              if (en1.hasAttribute("part")) {
                part = en1.getAttribute("part").charAt(0);
              } else {
                part = 'i';
              }
              break;
            case "L":
              if (inS) {
                startParagraph(p2);
              } else {
                startParagraph(p1);
              }
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
            case "HW":
              inHW = false;
              break;
            case "I":
              fontStack.pop();
              break;
            case "LD":
              startParagraph();
              break;
            case "S":
              inS = false;
              break;
            case "SD":
              fontStack.pop();
              inSD = false;
              break;
            case "SP":
              addChunk(". ");
              inSP = false;
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
            case "HW":
              inHW = true;
              break;
            case "I":
              font = new Font(fontStack.getFirst());
              font.setStyle(Font.ITALIC);
              fontStack.push(font);
              break;
            case "LD":
              startParagraph(ld);
              break;
            case "S":
              inS = true;
              break;
            case "SD":
              font = new Font(fontStack.getFirst());
              font.setStyle(Font.ITALIC);
              StartNode start = (StartNode) n;
              if (start.hasAttribute("t") && start.getAttribute("t").contains("exit")) {
                startParagraph(exit);
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
          }
          break;
        case TEXT:
          String txt = n.getText();
          txt = txt.replace("--", "\u2014");
          txt = txt.replace("\n", "");

          if (inSP) {
            addChunk(txt.toUpperCase());
            break;
          }

          if (inHW) {
            txt = txt.replaceFirst("[(]", "");
            inHW = false;
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
            } else {
              addChunk(txt);
              break;
            }
          }

          if (part != 'i' && inS) {
            RtfTab tab = null;
            String tabStr = "";
            if (part == 'm') {
              tab = new RtfTab(100, RtfTab.TAB_LEFT_ALIGN);
              tabStr = "\t";
            }
            if (part == 'f') {
              tab = new RtfTab(200, RtfTab.TAB_LEFT_ALIGN);
              tabStr = "\t\t";
            }
            if (tab != null) {
              p.add(tab);
              addChunk(tabStr);
            }
            part = 'i'; // ensure it's only done once for m or f.
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
                } else {
                  // typographer's start quote.
                  sb.append("\u201C");
                  inDQ = true;
                }
                break;
              case '\'':
                sb.append("\u2019"); // appostrophe
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
