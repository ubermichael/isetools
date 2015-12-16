/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
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

import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.document.Annotation;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.dom.Fragment;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.node.TextNode;
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
import com.lowagie.text.rtf.direct.RtfDirectContent;
import com.lowagie.text.rtf.style.RtfParagraphStyle;
import com.lowagie.text.rtf.text.RtfTab;
import java.awt.Color;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class RTFWriter extends Writer {

    private static final Logger logger = Logger.getLogger(RTFWriter.class.getName());

	private final String COMMON_PUNCT = ".,;:?!\"";
	
    private final Document doc;
    private final RtfWriter2 writer;
    private ArrayDeque<Font> fontStack;
    private Paragraph p = new Paragraph();

    private RtfParagraphStyle normal;
    private RtfParagraphStyle exit;
    private RtfParagraphStyle ld;
    private RtfParagraphStyle p1;
    private RtfParagraphStyle p2;
    private RtfParagraphStyle prose;
    private RtfParagraphStyle fnStyle;

    public RTFWriter() throws UnsupportedEncodingException, ParserConfigurationException {
        this(System.out);
    }

    public RTFWriter(PrintStream out) throws ParserConfigurationException, UnsupportedEncodingException {
        super(out);
        doc = new Document();
        writer = RtfWriter2.getInstance(doc, out);
        writer.getDocumentSettings().setOutputDebugLineBreaks(true);
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
        
        prose = new RtfParagraphStyle("ISE Prose", "ISE Normal");
        prose.setFirstLineIndent(-19);
        prose.setIndentLeft(19);
        prose.setIndentRight(49);
        writer.getDocumentSettings().registerParagraphStyle(prose);

        p2 = new RtfParagraphStyle("ISE p2", "ISE Normal");
        p2.setFirstLineIndent(-19);
        p2.setIndentLeft(38);
        p2.setIndentRight(49);
        writer.getDocumentSettings().registerParagraphStyle(p2);

        fnStyle = new RtfParagraphStyle("ISE Footnote", "ISE Normal");
        fnStyle.setFontName("Times New Roman");
        fnStyle.setFirstLineIndent(-19);
        fnStyle.setIndentLeft(19);
        fnStyle.setIndentRight(0);
        fnStyle.setSize(10);        
        writer.getDocumentSettings().registerParagraphStyle(fnStyle);
    }

    private void startParagraph() throws DocumentException {
        startParagraph(normal);
    }

    private void startParagraph(RtfParagraphStyle style) throws DocumentException {
        if (!p.isEmpty() && !StringUtils.isWhitespace(p.getContent())) {
            doc.add(p);
        }
        p = new Paragraph("", style);
    }

    private void addChunk(String txt) {
        if (txt.length() > 0) {
            Chunk c = new Chunk(txt, fontStack.getFirst());
            p.add(c);
        }
    }
    
    private void addDirect(String rtf) {
        if(rtf.length() > 0) {
            RtfDirectContent c = new RtfDirectContent(rtf);
            p.add(c);
        }
    }

    private void footnote(Note note) throws IOException, DocumentException {
        Footnote fn = new Footnote("", fnStyle);
        fn.add(new Chunk(note.getNote("1").unicode().trim()));
        p.add(fn);
    }

    @ErrorCode(code = {"rtfwriter.note.notfound"})
    private void preprocess(DOM dom, Annotation ann) throws IOException {
        int i = 0;
        for (Note note : ann) {

            String lemmaSrc = note.getLem();
            if (note.isLemSplit()) {
                lemmaSrc = note.getLemEnd();
            }

            String lemmaStr = new DOMBuilder(lemmaSrc).build().unicode();
            String tlnStr = note.getTln();
            int length = 2;
            if (note.isTlnSplit()) {
                tlnStr = note.getTlnStart();
                length = 6;
            }

            Fragment frag = dom.getTlnFragment(tlnStr, length);
            Node tln = dom.getTln(tlnStr);
            int offset = frag.unicode().indexOf(lemmaStr);
            if (offset == -1) {
                Message m = Message.builder("rtfwriter.note.notfound")
                        .addNote("Cannot find lemma '" + lemmaStr + "' near TLN " + tlnStr)
                        .addNote("lemSplit: " + note.isLemSplit() + " tlnSplit: " + note.isTlnSplit())
                        .addNote(note.toString())
                        .build();
                Log.addMessage(m);
                continue;
            }
            offset += lemmaStr.length();
            Node n = tln;
            while (offset > 0) {
                n = dom.get(n.getPosition() + 1);
                if (n instanceof TextNode) {
                    offset -= n.getText().length();
                }
            }			
            String txt = n.getText();
			if((txt.length() + offset < txt.length()) && COMMON_PUNCT.contains(""+txt.charAt(txt.length() + offset))) {
				offset++;
			}
			
            EmptyNode fn = new EmptyNode("FNLOC");
            fn.setAttribute("ref", "" + note.getId());
            dom.splitTextNode((TextNode) n, txt.length() + offset, fn);
        }
    }

    @Override
    public void render(DOM dom) throws DocumentException, IOException {
        render(dom, Annotation.builder().build());
    }

    @Override
    public void render(DOM dom, Annotation annotation) throws DocumentException, IOException {
        this.preprocess(dom, annotation);

        fontStack = new ArrayDeque<>();
        fontStack.push(FontFactory.getFont("Times New Roman", 12, Color.BLACK));
        Font font;

        boolean inSP = false; // in speech prefix
        boolean inSD = false; // in stage direction
        boolean inDQ = false; // in a double quote
        boolean inS = false; // in a speech
        boolean inHW = false;
        char part = 'i';

        String mode = "verse";

        doc.open();
        startParagraph();

        for (Node n : dom) {
            switch (n.type()) {
                case ABBR:
                    break;
                case CHAR:
                    addChunk(n.unicode());
                    break;
                case EMPTY:
                    switch (n.getName()) {
                        case "FNLOC":
                            EmptyNode fnloc = (EmptyNode) n;
                            Note note = annotation.get(Integer.parseInt(fnloc.getAttribute("ref")) - 1);
                            if (!note.hasNoteLevel("1")) {
                                break;
                            }
                            this.footnote(note);                            
                            break;
                        case "TLN":
                        case "L":
                            if(mode.equals("prose")) {
                                break;
                            }
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
                        case "MODE":
                            mode = ((TagNode)n).getAttribute("t");
                            break;
                        case "S":
                            if(mode.equals("prose")) {
                                startParagraph(prose);
                            }
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
                        // DOES NOT MATCH AFTER A TAG.
                        if ((txt.indexOf('[') >= 0) || (txt.indexOf(']') >= 0)) {
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < txt.length(); i++) {
                                char c = txt.charAt(i);
                                if (c == '[' || c == ']') {
                                    if (sb.length() > 0) {
                                        addChunk(sb.toString());
                                        sb = new StringBuilder();
                                    }
                                    addDirect("{\\i0" + String.valueOf(c) + "}");
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
