package ca.nines.ise.writer;

import ca.nines.ise.dom.DOM;
import nu.xom.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class XMLWriterReorderingTest extends XMLWriterTestBase {

    @Test
    @Ignore
    public void moveCatchwordToPage() {

    }
    @Test
    @Ignore
    public void moveRunningTitleToPage() {

    }
    @Test
    @Ignore
    public void moveSignatureToPage() {

    }
    @Test
    @Ignore
    public void movePageNumberToPage() {

    }

    @Test
    @Ignore
    public void flattenTypefaces() {
        Document output = render("<WORK><BLL>a<R>b</R>c</BLL></WORK>");
        // output <work><l><bll>a</bll><r>b</r><bll>c</bll></work>
        output = render("<WORK><R>a<BLL>b</BLL>c</R></WORK>");
        // sim.
        // handle /redundant/ tagging in a transformer step
    }

    @Test
    @Ignore
    public void flattenFontsize() {
        Document output = render("<WORK><FONT size=\"1\">a<FONT size=\"2\">b</FONT>c</FONT></WORK>");
        // output <work><l><font size="1">a</font><font size="2">b</font><font size="1">c</font></work>        
    }

    @Test
    @Ignore
    public void breakInlinesAroundMarg() {
        // in both <l> and <label>
    }

    @Test
    @Ignore
    public void splitSpeechesByLine() {

    }

    @Test
    @Ignore
    public void moveLabelOutOfLine() {
        // <BRACEGROUP>                     --> <bracegroup>
        // hello <LABEL>test</LABEL> world        <label>test</label> <l>hello world </l>
        // goodbye                                <l>goodbye </l>
        // </BRACEGROUP>                        </bracegroup>
    }

}
