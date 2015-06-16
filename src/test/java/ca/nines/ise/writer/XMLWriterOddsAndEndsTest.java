package ca.nines.ise.writer;

import ca.nines.ise.dom.DOM;
import nu.xom.*;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.regex.Pattern;


public class XMLWriterOddsAndEndsTest extends XMLWriterTestBase {

    // SPECIAL CHARS
    @Test
    public void handleSpaceEscapes() {
        Document output = render("<WORK>{ }{#}</WORK>");
        Nodes line = output.query("//"+DOC_PREFIX+":l", NS_MAP);
        assertEquals(
            "{ } translated to extra space",
            ((Element)line.get(0).getChild(0)).getAttributeValue("t"),
            "extra"
        );
        assertEquals(
        	"{#} translated to missing space",
            ((Element)line.get(0).getChild(1)).getAttributeValue("t"),
            "missing"
        );
    }
    
    @Test
    public void handleShyEscape() {
        Document output = render("<WORK>a{-}\nb\nc</WORK>");
        Nodes shy = output.query("//"+DOC_PREFIX+":shy", NS_MAP);
        assertEquals(
            "one shy was generated",
            shy.size(),
            1
        );
    }
    
    @Test
    @Ignore
    public void handleLigatureEscapes() {
        // {ae} --> <lig unicode="æ">ae</lig>
    }
    
    @Test
    @Ignore
    public void handleTypeformEscapes() {
        // {W} --> <typeform set="VV">W</typeform>
    }

    @Test
    public void spaceLowercase() {
        Document output = render("<WORK><SPACE/></WORK>");
        Nodes space = output.query("//"+DOC_PREFIX+":space", NS_MAP);
        assertEquals(
        	"space generated",
        	space.size(),
            1
        );
    }
    
    @Test
    public void missingSpaceContainsWhitespace() {
        Document output = render("<WORK><SPACE/></WORK>");
        Nodes space = output.query("//"+DOC_PREFIX+":space", NS_MAP);
        assertTrue(
            "space contains whitespace",
            Pattern.matches("\\s+", space.get(0).getValue())
        );    
        // <SPACE/> --> <space> </space>
    }

    @Test
    public void ruleLowercase() {
        Document output = render("<WORK><RULE/></WORK>");
        Nodes rule = output.query("//"+DOC_PREFIX+":rule", NS_MAP);
        assertEquals(
            "rule generated",
            rule.size(),
            1
        );    
    }

    // BR ELEMENT (self-closing, must be in HW)
    @Test
    @Ignore
    public void brLowercase() {

    }

    @Test
    public void labelLowercase() {
        Document output = render("<WORK><BRACEGROUP><LABEL>b</LABEL></BRACEGROUP></WORK>");
        Nodes label = output.query("//"+DOC_PREFIX+":label", NS_MAP);
        assertEquals(
            "label generated",
            label.size(),
            1
        );
    }
    
    @Test
    public void labelIsNotInline() {
        Document output = render("<WORK><BRACEGROUP><LABEL>b</LABEL></BRACEGROUP></WORK>");
        Nodes lines = output.query("//"+DOC_PREFIX+":l", NS_MAP);
        assertEquals(
            "no line is generated",
            lines.size(),
            0
        );
    }

    // ILINK/IEMBED
    // not checking namespaces right now
    @Test
    public void ilinkNamespaced() {
        Document output = render("<WORK><ILINK></ILINK></WORK>");
        Nodes ilink = output.query("//"+LINK_PREFIX+":ilink", NS_MAP);
        assertEquals(
            "ilink generated",
            ilink.size(),
            1
        );
    }
    @Test
    public void ilinkIsInline() {
        Document output = render("<WORK><ILINK></ILINK></WORK>");
        Nodes line = output.query("//"+DOC_PREFIX+":l", NS_MAP);
        Nodes ilink = output.query("//"+LINK_PREFIX+":ilink", NS_MAP);
        assertEquals(
            "ilink generated in line",
            line.get(0).getChild(0),
            ilink.get(0)
        );
    }
    @Test
    public void iembedNamespaced() {
        Document output = render("<WORK><IEMBED></IEMBED></WORK>");
        Nodes iembed = output.query("//"+LINK_PREFIX+":iembed", NS_MAP);
        assertEquals(
            "iembed generated",
            iembed.size(),
            1
        );
    }
    @Test
    public void iembedIsSometimesInline() {
        Document output = render("<WORK><IEMBED></IEMBED><L/><IEMBED></IEMBED></WORK>");
        Nodes lines = output.query("//"+DOC_PREFIX+":l", NS_MAP);
        Nodes iembed = output.query("//"+LINK_PREFIX+":iembed", NS_MAP);
        assertEquals(
            "one line generated",
            lines.size(),
            1
        );
        assertEquals(
        	"second iembed is in line",
        	lines.get(0).getChild(0),
        	iembed.get(1)
        );
    }

    // AMBIGUITIES
    @Test
    @Ignore
    public void lowercaseAmbigRdg() {
        // like XMLWriterInlinesTest#lowercaseInlines
    }
    @Test
    @Ignore
    public void wrapAmbigInline() {
        // like XMLWriterInlinesTest#wrapInlines
    }
    @Test
    @Ignore
    public void propagateAmbigIntoLines() {
        // sim to XMLWriterInlinesTest#propagateInlinesIntoLines
        // create a separate ambig/rdg for each line
        // need to handle uneven rdg's
    }

}
