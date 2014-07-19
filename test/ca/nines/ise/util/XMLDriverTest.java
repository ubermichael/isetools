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
package ca.nines.ise.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class XMLDriverTest {

  @Test
  public void testDriveFile() throws ParserConfigurationException, SAXException, TransformerException, IOException {
    XMLDriver xd = new XMLDriver();
    Document document = xd.drive(new File("test/resources/data/test-errors.xml"));
    
    Element root = document.getDocumentElement();
    LocationData loc = (LocationData) root.getUserData(LocationData.LOCATION_DATA_KEY);    
    assertNotNull(loc);
    assertTrue(loc.getSystemId().endsWith("test-errors.xml"));
    assertTrue(loc.getStartColumn() >= 1);
    assertTrue(loc.getEndColumn() >= 1);
    
    Element msg = (Element) document.getElementsByTagName("message").item(0);
    loc = (LocationData) root.getUserData(LocationData.LOCATION_DATA_KEY);
    assertTrue(loc.getSystemId().endsWith("test-errors.xml"));
    assertTrue(loc.getStartColumn() >= 1);
    assertTrue(loc.getEndColumn() >= 1);
  }

  @Test
  public void testDriveResource() throws ParserConfigurationException, SAXException, TransformerException, IOException {
    XMLDriver xd = new XMLDriver();
    String res = "/resources/data/test-errors.xml";
    Document document = xd.drive(res, getClass().getResourceAsStream(res));
    
    Element root = document.getDocumentElement();
    LocationData loc = (LocationData) root.getUserData(LocationData.LOCATION_DATA_KEY);    
    assertNotNull(loc);
    assertTrue(loc.getSystemId().endsWith("test-errors.xml"));
    assertTrue(loc.getStartColumn() >= 1);
    assertTrue(loc.getEndColumn() >= 1);
    
    Element msg = (Element) document.getElementsByTagName("message").item(0);
    loc = (LocationData) root.getUserData(LocationData.LOCATION_DATA_KEY);
    assertTrue(loc.getSystemId().endsWith("test-errors.xml"));
    assertTrue(loc.getStartColumn() >= 1);
    assertTrue(loc.getEndColumn() >= 1);
  }
}
