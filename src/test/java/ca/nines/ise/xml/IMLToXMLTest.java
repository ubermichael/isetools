package ca.nines.ise.xml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Test;

import static org.junit.Assert.*;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.dom.DOM.DOMStatus;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.normalizer.NormalizingFactory;
import ca.nines.ise.writer.Writer;
import ca.nines.ise.writer.XMLWriterNew;
import nu.xom.*;
import nu.xom.canonical.Canonicalizer;

public class IMLToXMLTest {
	
	public static final String input_path = "src/test/resources/data/xml_tests/input/";
	public static final String output_path = "src/test/resources/data/xml_tests/output/";
	public static final String input_ext = ".txt";
	public static final String output_ext = ".xml";
	
	/**
	 * Returns IML file as an IML DOM
	 * @param file IML file
	 * @return IML DOM
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private DOM get_iml_dom(File file) throws FileNotFoundException, IOException{
    	DOM dom = new DOMBuilder(file).build();
    	assertNotEquals(dom.getStatus(), DOMStatus.ERROR);
    	return dom;
	}
	
	/**
	 * Returns an IML DOM as a XOM document
	 * @param iml IML DOM
	 * @return XOM document
	 * @throws TransformerConfigurationException
	 * @throws TransformerException
	 * @throws IOException
	 * @throws Exception
	 */
	private Document get_doc_from_dom(DOM iml) throws TransformerConfigurationException, TransformerException, IOException, Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    new XMLWriterNew(new PrintStream(baos)).render(iml);
	    String xml = baos.toString();
	    //System.out.println(xml);
	    assertNotEquals(xml,"");
	    return new Builder(new NormalizingFactory()).build(xml, null);
	}
	
	/**
	 * Converts a XOM document to a string in canonical XML form
	 * @param doc XOM document
	 * @return document as a string
	 * @throws IOException
	 */
	private String doc_to_string(Document doc) throws IOException{ 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Canonicalizer canon = new Canonicalizer(new PrintStream(baos), false);
        canon.write(doc);
	    String xml = baos.toString();
	    assertNotEquals(xml,"");
	    return xml;
	}
	
	/**
	 * asserts that two xom documents are equal after being converted to
	 * canonical xml
	 * @param xml1 doc1
	 * @param xml2 doc2
	 * @throws IOException
	 */
	private void compare_xml(Document xml1, Document xml2) throws IOException{
		//System.out.println("\n"+doc_to_string(xml1)+"\n"+doc_to_string(xml2));
		assertEquals(doc_to_string(xml1),doc_to_string(xml2));
	}
	
	/**
	 * performs the actual testing for the given test
	 * @param testfile the test file name without its extension
	 * @throws TransformerConfigurationException
	 * @throws TransformerException
	 * @throws Exception
	 */
	private void test(String testfile) throws TransformerConfigurationException, TransformerException, Exception{
		DOM iml_dom = get_iml_dom(new File(input_path+testfile+input_ext));
		Document xml_test = new Builder(new NormalizingFactory()).build(new File(output_path+testfile+output_ext));
		Document xml_out = get_doc_from_dom(iml_dom);
		compare_xml(xml_test,xml_out);
	}
	
	@Test
	public void abbr() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_1");
	}
	
	@Test
	public void act() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_2");
	}

	@Test
	public void ambig() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_3");
	}
	
	@Test
	public void backmatter() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_4");
	}	
	
	@Test
	public void bll() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_5");
	}	
	
	//@Test
	public void br() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_6");
	}	
	
	@Test
	public void c() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_7");
	}	
	
	@Test
	public void cl() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_8");
	}
	
	//@Test
	public void col() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_9");
	}

	@Test
	public void cw() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_10");
	}	
	
	@Test
	public void div() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_11");
	}
	
	@Test
	public void em() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_12");
	}
	
	//@Test
	public void fontgroup() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_13");
	}		
	
	//@Test
	public void foreign() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_14");
	}	
	
	@Test
	public void frontmatter() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_15");
	}		
	
	@Test
	public void hw() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_16");
	}	
	
	//@Test
	public void i() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_17");
	}
	
	//@Test
	public void iembed() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_18");
	}
	
	//@Test
	public void ilink() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_19");
	}
	
	//@Test
	public void indent() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_20");
	}
	
	//@Test
	public void iseheader() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_21");
	}
	
	@Test
	public void j() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_22");
	}
	
	@Test
	public void l() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_23");
	}
	
	@Test
	public void ld() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_24");
	}
	//@Test
	public void linegroup() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_25");
	}
	//@Test
	public void link() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_26");
	}
	@Test
	public void ls() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_27");
	}
	//@Test
	public void marg() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_28");
	}
	//@Test
	public void meta() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_29");
	}
	@Test
	public void mode() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_30");
	}
	//@Test
	public void ornament() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_31");
	}
	//@Test
	public void page() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_32");
	}
	//@Test
	public void prop() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_33");
	}
	//@Test
	public void qln() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_34");
	}
	//@Test
	public void quote() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_35");
	}
	//@Test
	public void r() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_36");
	}
	@Test
	public void ra() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_37");
	}
	@Test
	public void rt() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_38");
	}
	//@Test
	public void rule() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_39");
	}
	@Test
	public void s() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_40");
	}
	//@Test
	public void sc() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_41");
	}
	@Test
	public void scene() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_42");
	}
	@Test
	public void sd() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_43");
	}
	@Test
	public void sig() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_44");
	}
	//@Test
	public void sp() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_45");
	}
	//@Test
	public void space() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_46");
	}
	//@Test
	public void stanza() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_47");
	}
	//@Test
	public void sub() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_48");
	}
	//@Test
	public void ssup() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_49");
	}
	//@Test
	public void swash() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_50");
	}
	//@Test
	public void titlehead() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_51");
	}
	//@Test
	public void tln() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_52");
	}
	//@Test
	public void wln() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_53");
	}
	@Test
	public void work() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_54");
	}
	//@Test
	public void lig() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_55");
	}
	//@Test
	public void typeform() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_56");
	}
	//@Test
	public void extraSpace() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_57");
	}	
	//@Test
	public void dash() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_58");
	}
	@Test
	public void splitline() throws TransformerConfigurationException, TransformerException, Exception{
		test("test_59");
	}
	
}
