package ca.nines.ise.output;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
/**
 *
 * @author michael
 */
public class XMLOutput {

  private final DocumentBuilderFactory docFactory;
  private final DocumentBuilder docBuilder;
  private final PrintStream out;

  public XMLOutput() throws ParserConfigurationException, UnsupportedEncodingException {
    this(new PrintStream(System.out, true, "UTF-8"));
  }

  public XMLOutput(PrintStream out) throws ParserConfigurationException {
    docFactory = DocumentBuilderFactory.newInstance();
    docBuilder = docFactory.newDocumentBuilder();
    this.out = out;
  }

  public void render(DOM dom) throws TransformerConfigurationException, TransformerException {
    // @TODO check if the DOM is expanded, and expand if necessary.

    Iterator<Node> iterator = dom.iterator();
    ArrayDeque<Element> xmlStack = new ArrayDeque<>();
    Document xml = docBuilder.newDocument();

    Element root = xml.createElement("work");
    xml.appendChild(root);
    xmlStack.push(root);
    
    while (iterator.hasNext()) {
      Node n = iterator.next();
      switch (n.type()) {
        case ABBR:
          break;
        case CHAR:
          break;
        case COMMENT:
          Comment c = xml.createComment(n.getText());
          xml.appendChild(c);
          break;
        case EMPTY:
          break;
        case END:
          System.out.println("size a: " + xmlStack.size());
          xmlStack.pop();
          System.out.println("size a: " + xmlStack.size());
          break;
        case START:
          StartNode sn = (StartNode) n;
          Element e = xml.createElement(sn.getName());
          for (String name : sn.getAttributeNames()) {
            e.setAttribute(name, sn.getAttribute(name));
          }
          System.out.println("size b: " + xmlStack.size());
          xmlStack.peekFirst().appendChild(e);
          System.out.println("size c: " + xmlStack.size());
          xmlStack.push(e);
          System.out.println("size d: " + xmlStack.size());
          break;
        case TEXT:
          Text t = xml.createTextNode(n.getText());
          System.out.println("size e: " + xmlStack.size());
          xmlStack.peekFirst().appendChild(t);
          System.out.println("size f: " + xmlStack.size());
          break;
      }
    }
    xml.normalizeDocument();

    try {
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource source = new DOMSource(xml);
      StreamResult stream = new StreamResult(out);
      transformer.transform(source, stream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
