package ca.nines.ise.validator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assume.assumeThat;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.schema.Schema;
import ca.nines.ise.validator.node.TestBase;

public class ValidatorTestBase extends TestBase{
  
  Schema schema;
  
  public ValidatorTestBase() throws TransformerConfigurationException, TransformerException, ParserConfigurationException, SAXException, IOException{    
    String loc = "/schemas/default.xml";
    InputStream stream = ValidatorTestBase.class.getResourceAsStream(loc);
    schema = Schema.builder().from(loc, stream).build();
  }
  
  public DOM getDOM(String iml) throws IOException{
    DOM d = new DOMBuilder(iml).build();
    assumeThat(d.getStatus(), is(DOM.DOMStatus.CLEAN));
    return d;
  }

}
