/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.util;

import java.util.ArrayDeque;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MutationEvent;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.LocatorImpl;

/**
 * Add location data to an XML node.
 * <p>
 * http://javacoalface.blogspot.ca/2011/04/line-and-column-numbers-in-xml-dom.html
 * <p>
 * @author michael
 */
public class LocationAnnotator extends XMLFilterImpl {

  private Locator locator;
  private final String source;
  private final ArrayDeque<Locator> locatorStack = new ArrayDeque<>();
  private final ArrayDeque<Element> elementStack = new ArrayDeque<>();

  private final UserDataHandler dataHandler = new LocationDataHandler();

  LocationAnnotator(XMLReader xmlReader, Document dom) {
    super(xmlReader);
    source = "";

    EventListener modListener = new EventListener() {
      @Override
      public void handleEvent(Event e) {
        EventTarget target = ((MutationEvent) e).getTarget();
        elementStack.push((Element) target);
      }
    };
    ((EventTarget) dom).addEventListener("DOMNodeInserted", modListener, true);
  }

  LocationAnnotator(String source, XMLReader xmlReader, Document dom) {
    super(xmlReader);
    this.source = source;

    EventListener modListener = new EventListener() {
      @Override
      public void handleEvent(Event e) {
        EventTarget target = ((MutationEvent) e).getTarget();
        elementStack.push((Element) target);
      }
    };
    ((EventTarget) dom).addEventListener("DOMNodeInserted", modListener, true);
  }

  @Override
  public void setDocumentLocator(Locator locator) {
    super.setDocumentLocator(locator);
    this.locator = locator;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
    super.startElement(uri, localName, qName, atts);
    locatorStack.push(new LocatorImpl(locator));
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    super.endElement(uri, localName, qName);

    if (locatorStack.size() > 0) {
      Locator startLocator = locatorStack.pop();

      LocationData location = new LocationData(
              (startLocator.getSystemId() == null ? source : startLocator.getSystemId()),
              startLocator.getLineNumber(),
              startLocator.getColumnNumber(),
              locator.getLineNumber(),
              locator.getColumnNumber()
      );
      Element e = elementStack.pop();
      e.setUserData(
              LocationData.LOCATION_DATA_KEY, location,
              dataHandler);
    }
  }

  // Ensure location data copied to any new DOM node.
  private class LocationDataHandler implements UserDataHandler {

    @Override
    public void handle(short operation, String key, Object data, Node src, Node dst) {

      if (src != null && dst != null) {
        LocationData locatonData = (LocationData) src.getUserData(LocationData.LOCATION_DATA_KEY);

        if (locatonData != null) {
          dst.setUserData(LocationData.LOCATION_DATA_KEY, locatonData, dataHandler);
        }
      }
    }
  }
}
