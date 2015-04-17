package ca.nines.ise.writer;

import ca.nines.ise.dom.DOM;
import nu.xom.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.regex.Pattern;


public class XMLWriterOddsAndEndsTest extends XMLWriterTestBase {

    // SPECIAL CHARS
    @Test
    @Ignore
    public void handleSpaceEscapes() {
        // eg { }, {#} --> <space t="extra"/>, <space t="missing"> </space>
    }
    @Test
    @Ignore
    public void handleShyEscape() {
        // {-} --> <shy/>
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

    // SPACE ELEMENT (self-closing)
    @Test
    @Ignore
    public void spaceLowercase() {
        // <SPACE/> --> <space/>
    }
    @Test
    @Ignore
    public void formattingSpaceContainsWhitespace() {
        // <SPACE/> --> <space> </space>
    }

    // RULE ELEMENT (self-closing)
    @Test
    @Ignore
    public void ruleLowercase() {
        // <RULE/> --> <rule/>
    }

    // BR ELEMENT (self-closing, must be in HW)
    @Test
    @Ignore
    public void brLowercase() {

    }

    // LABLE ELEMENT
    @Test
    @Ignore
    public void labelLowercase() {
        // <BRACEGROUP><LABEL>a</LABEL></BRACEGROUP>
    }
    @Test
    @Ignore
    public void labelIsNotInline() {

    }

    // ILINK/IEMBED
    @Test
    @Ignore
    public void ilinkNamespaced() {
        // <ilink/> --> <link:ilink/>
    }
    @Test
    @Ignore
    public void ilinkIsInline() {
        // <ilink/> --> <l><link:ilink/></l>
    }
    @Test
    @Ignore
    public void iembedNamespaced() {
        // <iembed/> --> <link:iembed/>
    }
    @Test
    @Ignore
    public void iembedIsSometimesInline() {
        // <iembed/> --> <link:iembed/>
        // but
        // a <iembed/> --> <l>a <link:iembed/></l>
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
