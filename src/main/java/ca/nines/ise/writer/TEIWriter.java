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

import ca.nines.ise.document.Annotation;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.AbbrNode;
import ca.nines.ise.node.CharNode;
import ca.nines.ise.node.CommentNode;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TagNode;
import ca.nines.ise.node.TextNode;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.collections4.iterators.PeekingIterator;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class TEIWriter extends Writer {

    private static final Logger logger = Logger.getLogger(TEIWriter.class.getName());

    private static final String TEINS = "http://www.tei-c.org/ns/1.0";

    private PeekingIterator<Node> iterator;

    DOM dom;

    int joinID = 1;

    ArrayDeque<Element> xmlStack = new ArrayDeque<>();
    
    Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

    public TEIWriter() throws ParserConfigurationException, UnsupportedEncodingException {
        this(new PrintStream(System.out, true, "UTF-8"));
    }

    public TEIWriter(PrintStream out) throws ParserConfigurationException, UnsupportedEncodingException {
        super(out);
    }

    private Element teiHeader(DOM dom, Document xml) {
        Element teiHeader = xml.createElementNS(TEINS, "teiHeader");

        Element fileDesc = xml.createElementNS(TEINS, "fileDesc");
        teiHeader.appendChild(fileDesc);

        Element titleStmt = xml.createElementNS(TEINS, "titleStmt");
        titleStmt.setTextContent("Find title from meta[@name='dc.title']/@content");
        fileDesc.appendChild(titleStmt);

        return teiHeader;
    }

    private void append(org.w3c.dom.Node n) {
        xmlStack.peek().appendChild(n);
    }
    
    private void push(Element n) {
        xmlStack.peek().appendChild(n);
        xmlStack.push(n);
    }
    
    private Element peek() {
        return xmlStack.peek();
    }
    
    private Element pop() {
        return xmlStack.pop();
    }
    
    // -----------------------------------

    public void abbreviation(AbbrNode n) {

    }

    // -----------------------------------

    public void character(CharNode n) {
        Element g = xml.createElementNS(TEINS, "g");
        g.setAttribute("ref", "?");
        try {
            g.setTextContent(n.unicode());
        } catch (IOException ex) {
            Logger.getLogger(TEIWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        append(g);
    }
    
    // -----------------------------------

    public void comment(CommentNode n) {
        append(xml.createComment(n.comment()));
    }

    // -----------------------------------

    public void empty_bl(EmptyNode n) {

    }

    public void empty_br(EmptyNode n) {

    }

    public void empty_l(EmptyNode n) {
        Element lb = xml.createElementNS(TEINS, "lb");
        if(n.hasAttribute("n")) {
            lb.setAttribute("n", n.getAttribute("n"));
            lb.setAttribute("type", "ln");
        }
        if(n.hasAttribute("part")) {
            lb.setAttribute("part", n.getAttribute("part"));
        }
    }

    public void empty_lb(EmptyNode n) {

    }

    public void empty_link(EmptyNode n) {

    }

    public void empty_meta(EmptyNode n) {

    }

    public void empty_ornament(EmptyNode n) {
        
    }

    public void empty_prop(EmptyNode n) {

    }

    public void empty_qln(EmptyNode n) {
        Element lb = xml.createElementNS(TEINS, "lb");
        lb.setAttribute("type", "qln");
        lb.setAttribute("n", n.getAttribute("n"));
        append(lb);
    }

    public void empty_rule(EmptyNode n) {

    }

    public void empty_space(EmptyNode n) {

    }

    public void empty_tln(EmptyNode n) {
        Element lb = xml.createElementNS(TEINS, "lb");
        lb.setAttribute("type", "tln");
        lb.setAttribute("n", n.getAttribute("n"));
        append(lb);
    }

    public void empty_wln(EmptyNode n) {
        Element lb = xml.createElementNS(TEINS, "lb");
        lb.setAttribute("type", "wln");
        lb.setAttribute("n", n.getAttribute("n"));
        append(lb);
    }

    // -----------------------------------

    public void end_abbr(EndNode n) {

    }

    public void end_act(EndNode n) {
        pop();
    }

    public void end_add(EndNode n) {

    }

    public void end_ambig(EndNode n) {

    }

    public void end_backmatter(EndNode n) {

    }

    public void end_bll(EndNode n) {

    }

    public void end_blockquote(EndNode n) {

    }

    public void end_c(EndNode n) {

    }

    public void end_cl(EndNode n) {

    }

    public void end_col(EndNode n) {

    }

    public void end_cw(EndNode n) {

    }

    public void end_div(EndNode n) {

    }

    public void end_em(EndNode n) {

    }

    public void end_epilogue(EndNode n) {

    }

    public void end_fontgroup(EndNode n) {

    }

    public void end_foreign(EndNode n) {

    }

    public void end_frontmatter(EndNode n) {

    }

    public void end_h1(EndNode n) {

    }

    public void end_h2(EndNode n) {

    }

    public void end_h3(EndNode n) {

    }

    public void end_h4(EndNode n) {

    }

    public void end_h5(EndNode n) {

    }

    public void end_h6(EndNode n) {

    }

    public void end_hw(EndNode n) {

    }

    public void end_i(EndNode n) {

    }

    public void end_iembed(EndNode n) {

    }

    public void end_indent(EndNode n) {

    }

    public void end_iseheader(EndNode n) {

    }

    public void end_j(EndNode n) {

    }

    public void end_ld(EndNode n) {

    }

    public void end_lem(EndNode n) {

    }

    public void end_linegroup(EndNode n) {

    }

    public void end_link(EndNode n) {

    }

    public void end_ls(EndNode n) {

    }

    public void end_marg(EndNode n) {

    }

    public void end_mode(EndNode n) {

    }

    public void end_ornament(EndNode n) {

    }

    public void end_page(EndNode n) {

    }

    public void end_pn(EndNode n) {

    }

    public void end_poem(EndNode n) {

    }

    public void end_prologue(EndNode n) {

    }

    public void end_prop(EndNode n) {

    }

    public void end_prosequote(EndNode n) {

    }

    public void end_quote(EndNode n) {

    }

    public void end_r(EndNode n) {

    }

    public void end_ra(EndNode n) {

    }

    public void end_rdg(EndNode n) {

    }

    public void end_rt(EndNode n) {

    }

    public void end_s(EndNode n) {
        pop();
    }

    public void end_sc(EndNode n) {

    }

    public void end_scene(EndNode n) {
        pop();
    }

    public void end_sd(EndNode n) {

    }

    public void end_section(EndNode n) {

    }

    public void end_sig(EndNode n) {

    }

    public void end_sp(EndNode n) {
        pop();
    }

    public void end_stanza(EndNode n) {

    }

    public void end_sub(EndNode n) {

    }

    public void end_sup(EndNode n) {

    }

    public void end_swash(EndNode n) {

    }

    public void end_titlehead(EndNode n) {

    }

    public void end_titlepage(EndNode n) {

    }

    public void end_versequote(EndNode n) {

    }

    public void end_work(EndNode n) {

    }

    // -----------------------------------

    public void start_abbr(StartNode n) {

    }

    public void start_act(StartNode n) {
        Element div = xml.createElementNS(TEINS, "div");
        div.setAttribute("type", "act");
        div.setAttribute("n", n.getAttribute("n"));
        push(div);
    }

    public void start_add(StartNode n) {

    }

    public void start_ambig(StartNode n) {

    }

    public void start_backmatter(StartNode n) {

    }

    public void start_bll(StartNode n) {

    }

    public void start_blockquote(StartNode n) {

    }

    public void start_c(StartNode n) {

    }

    public void start_cl(StartNode n) {

    }

    public void start_col(StartNode n) {

    }

    public void start_cw(StartNode n) {

    }

    public void start_div(StartNode n) {

    }

    public void start_em(StartNode n) {

    }

    public void start_epilogue(StartNode n) {

    }

    public void start_fontgroup(StartNode n) {

    }

    public void start_foreign(StartNode n) {

    }

    public void start_frontmatter(StartNode n) {

    }

    public void start_h1(StartNode n) {

    }

    public void start_h2(StartNode n) {

    }

    public void start_h3(StartNode n) {

    }

    public void start_h4(StartNode n) {

    }

    public void start_h5(StartNode n) {

    }

    public void start_h6(StartNode n) {

    }

    public void start_hw(StartNode n) {

    }

    public void start_i(StartNode n) {

    }

    public void start_iembed(StartNode n) {

    }

    public void start_ilink(StartNode n) {

    }

    public void start_indent(StartNode n) {

    }

    public void start_iseheader(StartNode n) {

    }

    public void start_j(StartNode n) {

    }

    public void start_ld(StartNode n) {

    }

    public void start_lem(StartNode n) {

    }

    public void start_linegroup(StartNode n) {

    }

    public void start_ls(StartNode n) {

    }

    public void start_marg(StartNode n) {

    }

    public void start_mode(StartNode n) {

    }

    public void start_ornament(StartNode n) {

    }

    public void start_page(StartNode n) {

    }

    public void start_pn(StartNode n) {

    }

    public void start_poem(StartNode n) {

    }

    public void start_prologue(StartNode n) {

    }

    public void start_prop(StartNode n) {

    }

    public void start_prosequote(StartNode n) {

    }

    public void start_quote(StartNode n) {

    }

    public void start_r(StartNode n) {

    }

    public void start_ra(StartNode n) {

    }

    public void start_rdg(StartNode n) {

    }

    public void start_rt(StartNode n) {

    }

    public void start_s(StartNode n) {
        Element sp = xml.createElementNS(TEINS, "sp");
        Node next = lookAhead();
        if(next instanceof StartNode) {
            sp.setAttribute("who", ((TagNode)next).getAttribute("norm"));
        }
        push(sp);
    }

    public void start_sc(StartNode n) {

    }

    public void start_scene(StartNode n) {
        Element div = xml.createElementNS(TEINS, "div");
        div.setAttribute("type", "scene");
        div.setAttribute("n", n.getAttribute("n"));
        push(div);
    }

    public void start_sd(StartNode n) {

    }

    public void start_section(StartNode n) {

    }

    public void start_sig(StartNode n) {

    }

    public void start_sp(StartNode n) {
        Element speaker = xml.createElementNS(TEINS, "speaker");
        push(speaker);
    }

    public void start_stanza(StartNode n) {

    }

    public void start_sub(StartNode n) {

    }

    public void start_sup(StartNode n) {

    }

    public void start_swash(StartNode n) {

    }

    public void start_titlehead(StartNode n) {

    }

    public void start_titlepage(StartNode n) {

    }

    public void start_versequote(StartNode n) {

    }

    public void start_work(StartNode n) {

    }

    // ---------------------------------------
    
    public void text(TextNode n) {
        Text text = xml.createTextNode(n.unicode());
        append(text);
    }

    // ---------------------------------------
    
    private void dispatch(Node n) {
        switch (n.type()) {
            case ABBR:
                abbreviation((AbbrNode) n);
                break;
            case CHAR:
                character((CharNode) n);
                break;
            case COMMENT:
                comment((CommentNode) n);
                break;
            case END:
                dispatch_end((EndNode) n);
                break;
            case EMPTY:
                dispatch_empty((EmptyNode) n);
                break;
            case START:
                dispatch_start((StartNode) n);
                break;
            case TEXT:
                text((TextNode) n);
                break;
        }
    }

    private void dispatch_empty(EmptyNode n) {
        switch (n.getName().toLowerCase()) {
            case "bl":
                empty_bl(n);
                break;
            case "br":
                empty_br(n);
                break;
            case "l":
                empty_l(n);
                break;
            case "lb":
                empty_lb(n);
                break;
            case "link":
                empty_link(n);
                break;
            case "meta":
                empty_meta(n);
                break;
            case "ornament":
                empty_ornament(n);
                break;
            case "prop":
                empty_prop(n);
                break;
            case "qln":
                empty_qln(n);
                break;
            case "rule":
                empty_rule(n);
                break;
            case "space":
                empty_space(n);
                break;
            case "tln":
                empty_tln(n);
                break;
            case "wln":
                empty_wln(n);
                break;
            default:
                // @TODO throw an error here.
                break;
        }
    }

    private void dispatch_end(EndNode n) {
        switch (n.getName().toLowerCase()) {
            case "abbr":
                end_abbr(n);
                break;
            case "act":
                end_act(n);
                break;
            case "add":
                end_add(n);
                break;
            case "ambig":
                end_ambig(n);
                break;
            case "backmatter":
                end_backmatter(n);
                break;
            case "bll":
                end_bll(n);
                break;
            case "blockquote":
                end_blockquote(n);
                break;
            case "c":
                end_c(n);
                break;
            case "cl":
                end_cl(n);
                break;
            case "col":
                end_col(n);
                break;
            case "cw":
                end_cw(n);
                break;
            case "div":
                end_div(n);
                break;
            case "em":
                end_em(n);
                break;
            case "epilogue":
                end_epilogue(n);
                break;
            case "fontgroup":
                end_fontgroup(n);
                break;
            case "foreign":
                end_foreign(n);
                break;
            case "frontmatter":
                end_frontmatter(n);
                break;
            case "h1":
                end_h1(n);
                break;
            case "h2":
                end_h2(n);
                break;
            case "h3":
                end_h3(n);
                break;
            case "h4":
                end_h4(n);
                break;
            case "h5":
                end_h5(n);
                break;
            case "h6":
                end_h6(n);
                break;
            case "hw":
                end_hw(n);
                break;
            case "i":
                end_i(n);
                break;
            case "iembed":
                end_iembed(n);
                break;
            case "indent":
                end_indent(n);
                break;
            case "iseheader":
                end_iseheader(n);
                break;
            case "j":
                end_j(n);
                break;
            case "ld":
                end_ld(n);
                break;
            case "lem":
                end_lem(n);
                break;
            case "linegroup":
                end_linegroup(n);
                break;
            case "link":
                end_link(n);
                break;
            case "ls":
                end_ls(n);
                break;
            case "marg":
                end_marg(n);
                break;
            case "mode":
                end_mode(n);
                break;
            case "ornament":
                end_ornament(n);
                break;
            case "page":
                end_page(n);
                break;
            case "pn":
                end_pn(n);
                break;
            case "poem":
                end_poem(n);
                break;
            case "prologue":
                end_prologue(n);
                break;
            case "prop":
                end_prop(n);
                break;
            case "prosequote":
                end_prosequote(n);
                break;
            case "quote":
                end_quote(n);
                break;
            case "r":
                end_r(n);
                break;
            case "ra":
                end_ra(n);
                break;
            case "rdg":
                end_rdg(n);
                break;
            case "rt":
                end_rt(n);
                break;
            case "s":
                end_s(n);
                break;
            case "sc":
                end_sc(n);
                break;
            case "scene":
                end_scene(n);
                break;
            case "sd":
                end_sd(n);
                break;
            case "section":
                end_section(n);
                break;
            case "sig":
                end_sig(n);
                break;
            case "sp":
                end_sp(n);
                break;
            case "stanza":
                end_stanza(n);
                break;
            case "sub":
                end_sub(n);
                break;
            case "sup":
                end_sup(n);
                break;
            case "swash":
                end_swash(n);
                break;
            case "titlehead":
                end_titlehead(n);
                break;
            case "titlepage":
                end_titlepage(n);
                break;
            case "versequote":
                end_versequote(n);
                break;
            case "work":
                end_work(n);
                break;
            default:
                // @TODO throw an error here.
                break;
        }
    }

    private void dispatch_start(StartNode n) {
        switch (n.getName().toLowerCase()) {
            case "abbr":
                start_abbr(n);
                break;
            case "act":
                start_act(n);
                break;
            case "add":
                start_add(n);
                break;
            case "ambig":
                start_ambig(n);
                break;
            case "backmatter":
                start_backmatter(n);
                break;
            case "bll":
                start_bll(n);
                break;
            case "blockquote":
                start_blockquote(n);
                break;
            case "c":
                start_c(n);
                break;
            case "cl":
                start_cl(n);
                break;
            case "col":
                start_col(n);
                break;
            case "cw":
                start_cw(n);
                break;
            case "div":
                start_div(n);
                break;
            case "em":
                start_em(n);
                break;
            case "epilogue":
                start_epilogue(n);
                break;
            case "fontgroup":
                start_fontgroup(n);
                break;
            case "foreign":
                start_foreign(n);
                break;
            case "frontmatter":
                start_frontmatter(n);
                break;
            case "h1":
                start_h1(n);
                break;
            case "h2":
                start_h2(n);
                break;
            case "h3":
                start_h3(n);
                break;
            case "h4":
                start_h4(n);
                break;
            case "h5":
                start_h5(n);
                break;
            case "h6":
                start_h6(n);
                break;
            case "hw":
                start_hw(n);
                break;
            case "i":
                start_i(n);
                break;
            case "iembed":
                start_iembed(n);
                break;
            case "ilink":
                start_ilink(n);
                break;
            case "indent":
                start_indent(n);
                break;
            case "iseheader":
                start_iseheader(n);
                break;
            case "j":
                start_j(n);
                break;
            case "ld":
                start_ld(n);
                break;
            case "lem":
                start_lem(n);
                break;
            case "linegroup":
                start_linegroup(n);
                break;
            case "ls":
                start_ls(n);
                break;
            case "marg":
                start_marg(n);
                break;
            case "mode":
                start_mode(n);
                break;
            case "ornament":
                start_ornament(n);
                break;
            case "page":
                start_page(n);
                break;
            case "pn":
                start_pn(n);
                break;
            case "poem":
                start_poem(n);
                break;
            case "prologue":
                start_prologue(n);
                break;
            case "prop":
                start_prop(n);
                break;
            case "prosequote":
                start_prosequote(n);
                break;
            case "quote":
                start_quote(n);
                break;
            case "r":
                start_r(n);
                break;
            case "ra":
                start_ra(n);
                break;
            case "rdg":
                start_rdg(n);
                break;
            case "rt":
                start_rt(n);
                break;
            case "s":
                start_s(n);
                break;
            case "sc":
                start_sc(n);
                break;
            case "scene":
                start_scene(n);
                break;
            case "sd":
                start_sd(n);
                break;
            case "section":
                start_section(n);
                break;
            case "sig":
                start_sig(n);
                break;
            case "sp":
                start_sp(n);
                break;
            case "stanza":
                start_stanza(n);
                break;
            case "sub":
                start_sub(n);
                break;
            case "sup":
                start_sup(n);
                break;
            case "swash":
                start_swash(n);
                break;
            case "titlehead":
                start_titlehead(n);
                break;
            case "titlepage":
                start_titlepage(n);
                break;
            case "versequote":
                start_versequote(n);
                break;
            case "work":
                start_work(n);
                break;
            default:
                // @TODO throw an error here.
                break;
        }
    }

    protected Node lookAhead() {
        return iterator.peek();
    }

    @Override
    public void render(DOM dom) throws TransformerConfigurationException, TransformerException, IOException, Exception {
        // @TODO check if the DOM is expanded, and expand if necessary.

        this.dom = dom.expanded();
        iterator = new PeekingIterator<>(dom.iterator());

        Element tei = xml.createElementNS(TEINS, "TEI");
        tei.appendChild(teiHeader(dom, xml));
        xml.appendChild(tei);
        xmlStack.push(tei);

        while (iterator.hasNext()) {
            Node n = iterator.next();
            dispatch(n);
        }

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            DOMSource source = new DOMSource(xml);
            StreamResult stream = new StreamResult(out);
            transformer.transform(source, stream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Unsupported.
     * <p>
     * @param dom
     * @param ann <p>
     * @throws TransformerConfigurationException
     * @throws TransformerException
     * @throws IOException
     * @throws Exception
     */
    @Override
    public void render(DOM dom, Annotation ann) throws TransformerConfigurationException, TransformerException, IOException, Exception {
        throw new UnsupportedOperationException("Not supported."); //To change body of generated methods, choose Tools | Templates.
    }

}
