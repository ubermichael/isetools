package ca.nines.ise.writer;

import ca.nines.ise.document.Annotation;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.EndNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import java.io.IOException;
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
import org.w3c.dom.NamedNodeMap;
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
public class XMLWriter extends Writer{

  private final DocumentBuilderFactory docFactory;
  private final DocumentBuilder docBuilder;

  public XMLWriter() throws ParserConfigurationException, UnsupportedEncodingException {
    this(new PrintStream(System.out, true, "UTF-8"));
  }

  public XMLWriter(PrintStream out) throws ParserConfigurationException, UnsupportedEncodingException {
    super(out);
    docFactory = DocumentBuilderFactory.newInstance();
    docBuilder = docFactory.newDocumentBuilder();
  }

  @Override
  public void render(DOM dom) throws TransformerConfigurationException, TransformerException, IOException, Exception {
    // @TODO check if the DOM is expanded, and expand if necessary.

    ArrayDeque<Element> xmlStack = new ArrayDeque<>();
    Document xml = docBuilder.newDocument();

    Element e = xml.createElement("root");
    xml.appendChild(e);
    xmlStack.push(e);

    int joinID = 1;

    for(Node n : dom.expanded()) {
      switch (n.type()) {
        case COMMENT:
          Comment c = xml.createComment(n.getText());
          xmlStack.peekFirst().appendChild(c);
          break;
        case EMPTY:
          EmptyNode emptyNode = (EmptyNode) n;
          Element endElement = xml.createElement(emptyNode.getName().toLowerCase());
          for (String name : emptyNode.getAttributeNames()) {
            endElement.setAttribute(name, emptyNode.getAttribute(name));
          }
          xmlStack.peekFirst().appendChild(endElement);
          break;
        case END:
          EndNode endNode = (EndNode) n;
          if (endNode.getName().toLowerCase().equals(xmlStack.peekFirst().getNodeName())) {
            xmlStack.pop();
            break;
          }

          ArrayDeque<Element> splitStack = new ArrayDeque<>();
          while (xmlStack.size() > 1) {
            Element split = xmlStack.pop();
            if (split.getNodeName().equals(endNode.getName().toLowerCase())) {
              break; // while 
            }           
            if (!split.hasAttribute("joinID")) {
              split.setAttribute("joinID", "" + joinID);
              joinID++;
            }
            splitStack.push(split);
          }

          while (splitStack.size() > 0) {
            Element popped = splitStack.pop();
            Element clone = xml.createElement(popped.getNodeName());
            NamedNodeMap attrs = popped.getAttributes();
            int numAttrs = attrs.getLength();
            for (int i = 0; i < numAttrs; i++) {
              clone.setAttribute(attrs.item(i).getNodeName(), attrs.item(i).getNodeValue());
            }
            xmlStack.peekFirst().appendChild(clone);
            xmlStack.push(clone);
          }

          break;
        case START:
          StartNode startNode = (StartNode) n;
          Element startElement = xml.createElement(startNode.getName().toLowerCase());
          for (String name : startNode.getAttributeNames()) {
            startElement.setAttribute(name, startNode.getAttribute(name));
          }
          xmlStack.peekFirst().appendChild(startElement);
          xmlStack.push(startElement);
          break;
        case TEXT:
          Text t = xml.createTextNode(n.getText());
          xmlStack.peekFirst().appendChild(t);
          break;
        default:
          throw new Exception("Cannot convert " + n.getName() + " to XML");
      }
    }

    try {
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "no");
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
      transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      DOMSource source = new DOMSource(xml);
      StreamResult stream = new StreamResult(out);
      transformer.transform(source, stream);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void render(DOM dom, Annotation ann) throws TransformerConfigurationException, TransformerException, IOException, Exception {
    throw new UnsupportedOperationException("Not supported."); //To change body of generated methods, choose Tools | Templates.
  }

}
