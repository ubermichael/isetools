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
package ca.nines.ise.util;

import java.util.ArrayDeque;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.LocatorImpl;

/**
 * Annotates a DOM with location data during the construction process.
 * <p>
 * http://javacoalface.blogspot.ca/2011/04/line-and-column-numbers-in-xml-dom.html
 * <p>
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class LocationAnnotator extends XMLFilterImpl {

  /**
   * Locator returned by the construction process.
   */
  private Locator locator;

  /**
   * The systemID of the XML.
   */
  private final String source;

  /**
   * Stack to hold the locators which haven't been completed yet.
   */
  private final ArrayDeque<Locator> locatorStack = new ArrayDeque<>();

  /**
   * Stack holding incomplete elements.
   */
  private final ArrayDeque<Element> elementStack = new ArrayDeque<>();

  /**
   * A data handler to add the location data.
   */
  private final UserDataHandler dataHandler = new LocationDataHandler();

  /**
   * Construct a location annotator for an XMLReader and Document. The systemID
   * is determined automatically.
   *
   * @param xmlReader the reader to use the annotator
   * @param dom the DOM to annotate
   */
  LocationAnnotator(XMLReader xmlReader, Document dom) {
    super(xmlReader);
    source = "";

    EventListener modListener = new EventListener() {
      @Override
      public void handleEvent(Event e) {
        EventTarget target = e.getTarget();
        elementStack.push((Element) target);
      }
    };
    ((EventTarget) dom).addEventListener("DOMNodeInserted", modListener, true);
  }

  /**
   * Construct a location annotator for an XMLReader and Document. The systemID
   * is NOT determined automatically.
   *
   * @param source the systemID of the XML
   * @param xmlReader the reader to use the annotator
   * @param dom the DOM to annotate
   */
  LocationAnnotator(String source, XMLReader xmlReader, Document dom) {
    super(xmlReader);
    this.source = source;

    EventListener modListener = new EventListener() {
      @Override
      public void handleEvent(Event e) {
        EventTarget target = e.getTarget();
        elementStack.push((Element) target);
      }
    };
    ((EventTarget) dom).addEventListener("DOMNodeInserted", modListener, true);
  }

  /**
   * Add the locator to the document during the parse.
   *
   * @param locator the locator to add
   */
  @Override
  public void setDocumentLocator(Locator locator) {
    super.setDocumentLocator(locator);
    this.locator = locator;
  }

  /**
   * Handle the start tag of an element by adding locator data.
   *
   * @param uri The systemID of the XML.
   * @param localName the name of the tag. unused.
   * @param qName the FQDN of the tag. unused.
   * @param atts the attributes of the tag. unused.
   * @throws SAXException
   */
  @Override
  public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
    super.startElement(uri, localName, qName, atts);
    locatorStack.push(new LocatorImpl(locator));
  }

  /**
   * Handle the end tag of an element by adding locator data.
   *
   * @param uri The systemID of the XML.
   * @param localName the name of the tag. unused.
   * @param qName the FQDN of the tag. unused.
   * @throws SAXException
   */
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

  /**
   * UserDataHandler to insert location data into the XML DOM.
   */
  private class LocationDataHandler implements UserDataHandler {

    /**
     * Handle an even during a parse. An even is a start/end/empty tag or some
     * data.
     *
     * @param operation unused.
     * @param key unused
     * @param data unused
     * @param src the source of the data
     * @param dst the destination of the data
     */
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
