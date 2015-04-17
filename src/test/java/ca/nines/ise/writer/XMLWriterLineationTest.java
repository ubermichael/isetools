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
    @Ignore
    public void generateLinesInsideMarg() {
        // marg can occur inside a line, but should still have lines itself!        
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
            Pattern.matches("\\s+$", firstL.getValue()) ||
            Pattern.matches("^\\s+", secondL.getValue())
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
        Document output = render("<WORK>a<SHY/> \n b</WORK>");
        Nodes lines = output.query("//"+DOC_PREFIX+":l", NS_MAP);
        assumeTrue(lines.size() == 2);
        assertTrue(
            "no whitespace at end of first line or start of second",
            !whitespaceAtEndOrStart(lines.get(0), lines.get(1))  
        );
    }

    @Test
    @Ignore
    public void generateEmptyLineForUnnumberedL() {
        Document output = render("<WORK><L/></WORK>");
        // check line is generated
        // check line is empty (.getChildElements().size() == 0)
    }

    @Test
    @Ignore
    public void wrapPartialLines() {
        Document output = render("<WORK><L part=\"i\" n=\"1\"/>\n<L part=\"f\" n=\"1\"/></WORK>");
        // check for <splitline> with two <l> children
    }

    @Test
    @Ignore
    public void applyAlignmentToLine() {

    }


    private void translateMilestone(String type) {

    }

    @Test
    @Ignore
    public void translateLToMS() {

    }
    @Test
    @Ignore
    public void translateQLNToMS() {
        
    }
    @Test
    @Ignore
    public void translateTLNToMS() {
        
    }
    @Test
    @Ignore
    public void translateWLNToMS() {
        
    }
    @Test
    @Ignore
    public void lowercaseMS() {

    }

    @Test
    @Ignore
    public void indentCreatesSpaceEveryLine() {
        // space/@t = null || "formatting"
    }

}
