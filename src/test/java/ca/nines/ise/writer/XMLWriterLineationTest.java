package ca.nines.ise.writer;

import ca.nines.ise.dom.DOM;
import nu.xom.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.is;
import java.util.regex.Pattern;


public class XMLWriterLineationTest extends XMLWriterTestBase {

    @Test
    public void generateLinesAroundText() {
        Document output = render("<WORK>a</WORK>");
        Nodes results = output.query("//"+DOC_PREFIX+":l", NS_MAP);
        assertTrue("generated a line", results.size() == 1);
        assertTrue(
            "line contains content",
            Pattern.matches("^\\s*a\\s*$", results.get(0).getValue())
        );
    }

    @Test
    public void generateLinesInsideMarg() {
    	Document output = render("<WORK>a<MARG>b</MARG>d<WORK>");
    	Nodes lines = output.query("//"+DOC_PREFIX+":l", NS_MAP);
    	Nodes marg = output.query("//"+DOC_PREFIX+":marg", NS_MAP);
    	assertThat(
    			"2 lines generated",
    			lines.size(),
    			is(2)
    	);
    	assertThat(
    			"last 2nd line is in MARG",
    			lines.get(1).getParent(),
    			is(marg.get(0))
    	);
    	assertThat(
    			"marg is a child of the first line",
    			marg.get(0).getParent(),
    			is(lines.get(0))
    	);
    }


    @Test
    public void ignoreBlankLines() {
        Document output = render("<WORK>   \n\t \n\na\n\n \n</WORK>");
        assertThat(
            "only one line generated",
            output.query("//"+DOC_PREFIX+":l", NS_MAP).size(),
            is(1)
        );
    }

    private boolean whitespaceAtEndOrStart(Node firstL, Node secondL) {
        return (
            Pattern.matches(".*\\s+$", firstL.getValue()) ||
            Pattern.matches("^\\s+.*", secondL.getValue())
        );
    }

    @Test
    public void splitLinesAtNewline() {
        Document output = render("<WORK>a\nb</WORK>");
        Nodes lines = output.query("//"+DOC_PREFIX+":l", NS_MAP);
        assertThat(
            "two lines produced by content around one newline character",
            lines.size(),
            is(2)
        );
        assertTrue(
            "whitespace at end of first line or at beginning of second",
            // it doesn't actually matter where or how much, as long as there's some
            whitespaceAtEndOrStart(lines.get(0), lines.get(1))
        );
    }

    @Test
    public void constrainWhitespaceWhenUsingShy() {
        Document output = render("<WORK>a{-} \n b</WORK>");
        Nodes lines = output.query("//"+DOC_PREFIX+":l", NS_MAP);
        assumeTrue(lines.size() == 2);
        assertTrue(
            "no whitespace at end of first line or start of second",
            !whitespaceAtEndOrStart(lines.get(0), lines.get(1))  
        );
    }

    @Test
    public void generateEmptyLineForUnnumberedL() {
        Document output = render("<WORK><L/></WORK>");
        Nodes lines = output.query("//"+DOC_PREFIX+":l", NS_MAP);
        assertThat(
        		// check line is generated
        		"line is generated",
        		lines.size(),
        		is(1)
        );
        assertThat(
                // check line is empty
        		"line is empty",
        		lines.get(0).getChildCount(),
        		is(0)
        );
    }

    @Test
    public void wrapPartialLines() {
        Document output = render("<WORK><L part=\"i\" n=\"1\"/>\n<L part=\"f\" n=\"1\"/></WORK>");
        Nodes splitlines = output.query("//"+DOC_PREFIX+":splitline", NS_MAP);
        Nodes lines = output.query("//"+DOC_PREFIX+":l", NS_MAP);
        assumeTrue(lines.size() == 2);
        assertThat(
        		"splitline is generated",
        		splitlines.size(),
        		is(1)
        );
        assertTrue(
                "lines are children of splitline",
        		lines.get(0).getParent() == splitlines.get(0) &&
        		lines.get(1).getParent() == splitlines.get(0)
        );
    }

    @Test
    public void applyAlignmentToLine() {
        Document output = render("<WORK>a<RA>\nb</RA>\nc</WORK>");
        Nodes lines = output.query("//"+DOC_PREFIX+":l", NS_MAP);
        String a1 = ((Element) lines.get(0)).getAttributeValue("align");
        String a2 = ((Element) lines.get(1)).getAttributeValue("align");
        String a3 = ((Element) lines.get(2)).getAttributeValue("align");
        assertTrue(
        		"first 2 lines have alignment",
        		a1 != null &&
        		a2 != null
        );
        assertTrue(
                "3rd line has no alignment",
        		a3 == null
        );
    }


    private void translateMilestone(String type) {
        Document output = render("<WORK><"+type.toUpperCase()+" n=\"1\"/></WORK>");
        Nodes ms = output.query("//"+DOC_PREFIX+":ms", NS_MAP);
        assumeTrue(ms.size() == 1);
        assertThat(
        		"ms element of type"+type+"generated",
        		((Element) ms.get(0)).getAttributeValue("t"),
        		is(type)
        );
    }

    @Test
    public void translateLToMS() {
    	translateMilestone("l");
    }
    @Test
    public void translateQLNToMS() {
    	translateMilestone("qln");
    }
    @Test
    public void translateTLNToMS() {
    	translateMilestone("tln");
    }
    @Test
    public void translateWLNToMS() {
    	translateMilestone("wln");
    }
    
    @Test
    public void lowercaseMS() {
        Document output = render("<WORK><MS t=\"tln\" n=\"1\"></MS></WORK>");
        Nodes ms = output.query("//"+DOC_PREFIX+":ms", NS_MAP);
        assertEquals(
        	"ms is generated",
        	ms.size(),
        	1
        );
    }

    @Test
    @Ignore
    public void indentCreatesSpaceEveryLine() {
        // space/@t = null || "formatting"
    }

}
