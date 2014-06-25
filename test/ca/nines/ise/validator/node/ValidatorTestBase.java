/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.validator.node;

import ca.nines.ise.log.Log;
import ca.nines.ise.schema.Schema;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Before;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
abstract public class ValidatorTestBase {

  protected final Schema schema;
  protected final Log log;
  
  public ValidatorTestBase() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    File f = new File("/resources/data/test-schema.xml");
    schema = new Schema(f);
    log = Log.getInstance();
  }

  @Before
  public void setUp() {
    log.clear();
  }

  public void checkLog(String[] codes) {
    if(codes.length != log.count()) {
      System.out.println(log);
    }
    assertEquals(codes.length, log.count());
    for(int i = 0; i < log.count(); i++) {
      assertEquals(codes[i], log.get(i).getCode());
    }
    log.clear();
  }
  
}
