package ca.nines.ise.writer;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.schema.Schema;
import nu.xom.*;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


@RunWith(Parameterized.class)
public class XMLWriterInlinesTest extends XMLWriterTestBase {

    @Parameters
    public static List<Object[]> inlineTags() {
    	List<String> tags = Arrays.asList(Schema.INLINE_TAGS);
    	List<Object[]> list2 = new ArrayList<Object[]>();
    	for(String t:tags)
    		list2.add(new Object[]{t});
    	return list2;
    }

    @Parameter
    public String tagName;


    @Test
    public void lowercaseInlines() {
        Document output = render("<WORK><"+tagName+">a</"+tagName+"></WORK>");
        Nodes tags = output.query("//"+DOC_PREFIX+":"+tagName.toLowerCase(), NS_MAP);
        assertTrue(
            tagName+" translated to lowercase",
            tags.size() > 0 // i'm too lazy to import more of hamcrest...
        );
    }

    @Test
    public void wrapInLine() {
        Document output = render("<WORK><"+tagName+">a</"+tagName+"></WORK>");
        Nodes tags = output.query("//"+DOC_PREFIX+":l/"+DOC_PREFIX+":"+tagName.toLowerCase(), NS_MAP);
        assertThat(
            "single line "+tagName+" wrapped in a line",
            tags.size(),
            is(1)
        );
        assertTrue(
            "tag still contains its content",
            Pattern.matches("^\\s*a\\s*$", tags.get(0).getValue())
        );
    }

    @Test
    public void propagateInlineIntoLines() {
        switch (tagName) {
            case "HW":
            case "ORNAMENT":
            case "PERSON":
            case "PLACE":
            case "PROP":
                // these never cross lines
                break;
            default:
                Document output = render("<WORK><"+tagName+">a\nb\nc</"+tagName+"></WORK>");
                Nodes tags = output.query("//"+DOC_PREFIX+":l/"+DOC_PREFIX+":"+tagName.toLowerCase(), NS_MAP);
                assertThat(
                    "multi-line "+tagName+" split across lines",
                    tags.size(),
                    is(3)
                );
        }
    }

}
