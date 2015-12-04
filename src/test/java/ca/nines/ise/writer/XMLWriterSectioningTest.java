package ca.nines.ise.writer;

import ca.nines.ise.dom.DOM;
import nu.xom.*;

import org.junit.*;
import org.xml.sax.SAXException;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


public class XMLWriterSectioningTest extends XMLWriterTestBase {

    // CONTAINERS
	private void testContainer(String name,String test) throws SAXException, ParserConfigurationException, TransformerException {
        Document output = render("<WORK>"+test+"</WORK>");
        Nodes line = output.query("//"+DOC_PREFIX+":l", NS_MAP);
        Nodes container = output.query("//"+DOC_PREFIX+":"+name, NS_MAP);
        assertEquals(
            name+" generated",
            container.size(),
            1
        );
        assertEquals(
        	"line generated and child of "+name,
        	container.get(0).getChild(0),
        	line.get(0)
        );
	}
	
    @Test
    public void quoteIsAContainer() throws SAXException, ParserConfigurationException, TransformerException {
    	testContainer("quote","<QUOTE>a</QUOTE>");
    }
    
    @Test
    public void linegroupIsAContainer() throws SAXException, ParserConfigurationException, TransformerException {
    	testContainer("linegroup","<LINEGROUP>a</LINEGROUP>");
    }
    
    @Test
    public void margIsAContainer() throws SAXException, ParserConfigurationException, TransformerException {
    	testContainer("marg","<MARG>a</MARG>");
    }
    
    @Test
    public void stanzaIsAContainer() throws SAXException, ParserConfigurationException, TransformerException {
    	testContainer("linegroup","<STANZA>a</STANZA>");
    }
    
    @Test
    public void bracegroupIsAContainer() throws SAXException, ParserConfigurationException, TransformerException {
    	testContainer("bracegroup","<BRACEGROUP>a</BRACEGROUP>");
    }

    // SPECIAL HANDLING OF STANZA
    @Test
    public void translateStanzaToLinegroup() throws SAXException, ParserConfigurationException, TransformerException {
        Document output = render("<WORK><STANZA>a</STANZA></WORK>");
        Nodes linegroup = output.query("//"+DOC_PREFIX+":linegroup", NS_MAP);
        assertEquals(
            "linegroup generated",
            linegroup.size(),
            1
        );
    }
    
    @Test
    public void translateStazaNumberIntoMS() throws SAXException, ParserConfigurationException, TransformerException {
        Document output = render("<WORK><STANZA n=\"1\">a</STANZA></WORK>");
        Nodes n = output.query("//"+DOC_PREFIX+":linegroup[@n]", NS_MAP);
        Nodes ms = output.query("//"+DOC_PREFIX+":ms", NS_MAP);
        assertEquals(
            "ms is generated",
            ms.size(),
            1
        );
        assertEquals(
            "linegroup has no attribute n",
            n.size(),
            0
        );
        assertEquals(
        	"milestone generated with t=stanza",
        	((Element)ms.get(0)).getAttributeValue("t"),
        	"stanza"
        );
    }

    // SECTIONING MILESTONES
    private void testAsMilestone(String name) throws SAXException, ParserConfigurationException, TransformerException {
        Document output = render("<WORK><"+name.toUpperCase()+
        								">a</"+name.toUpperCase()+
        						 "></WORK>");
        Nodes ms = output.query("//"+DOC_PREFIX+":"+name, NS_MAP);
        assertTrue(
            name+" generated",
            ms.size() > 0
        );
        assertEquals(
        	name+" is empty",
        	ms.get(0).getChildCount(),
        	0
        );
    }
    
    @Test
    public void frontmatterIsAMilestone() throws SAXException, ParserConfigurationException, TransformerException {
    	testAsMilestone("frontmatter");
    }
    @Test
    public void backmatterIsAMilestone() throws SAXException, ParserConfigurationException, TransformerException {
    	testAsMilestone("backmatter");

    }
    @Test
    public void divIsAMilestone() throws SAXException, ParserConfigurationException, TransformerException {
    	testAsMilestone("div");
	}
    @Test
    public void actIsAMilestone() throws SAXException, ParserConfigurationException, TransformerException {
    	testAsMilestone("act");
    }
    @Test
    public void sceneIsAMilestone() throws SAXException, ParserConfigurationException, TransformerException {
    	testAsMilestone("scene");
    }
    @Test
    public void pageIsAMilestone() throws SAXException, ParserConfigurationException, TransformerException {
    	testAsMilestone("page");
    }
    @Test
    public void modeIsAMilestone() throws SAXException, ParserConfigurationException, TransformerException {
    	testAsMilestone("mode");
    }
    @Test
    public void colIsAMilestone() throws SAXException, ParserConfigurationException, TransformerException {
    	testAsMilestone("col");
    }

    // MAIN MILESTONE
    @Test
    public void createMainMilestoneAfterFrontmatter() throws SAXException, ParserConfigurationException, TransformerException {
        Document output = render("<WORK><FRONTMATTER>a</FRONTMATTER></WORK>");
        Nodes work = output.query("//"+DOC_PREFIX+":work", NS_MAP);
        Nodes main = output.query("//"+DOC_PREFIX+":main", NS_MAP);
		assertTrue(
			"main generated",
			main.size() > 0
		);
		assertEquals(
			"main is last element (after frontmatter)",
			work.get(0).getChild(work.get(0).getChildCount()-1),
			main.get(0)
		);
    }

    // CLOSING MODE MILESTONE
    @Test
    public void resetModeAfterClosing() throws SAXException, ParserConfigurationException, TransformerException {
        Document output = render("<WORK><MODE t=\"prose\">a</MODE>b</WORK>");
        Nodes modes = output.query("//"+DOC_PREFIX+":mode", NS_MAP);
		assertTrue(
			"2 modes generated",
			modes.size() == 2
		);
		assertEquals(
			"first mode is prose",
			((Element)modes.get(0)).getAttributeValue("t"),
			"prose"
		);
		assertEquals(
			"second mode is uncertain",
			((Element)modes.get(1)).getAttributeValue("t"),
			"uncertain"
		);
    }

    // SPECIAL COLUMN HANDLING
    @Test
    public void renameCol0ToReset() throws SAXException, ParserConfigurationException, TransformerException {
        Document output = render("<WORK><COL n=\"0\"></COL></WORK>");
        Nodes colr = output.query("//"+DOC_PREFIX+":col-reset", NS_MAP);
		assertTrue(
			"col-reset generated",
			colr.size() == 1
		);
	}
}