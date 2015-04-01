package ca.nines.ise.writer;

import ca.nines.ise.dom.DOM;
import nu.xom.*;

import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import java.util.regex.Pattern;

public class XMLWriterReorderingTest extends XMLWriterTestBase {

	private void translatePageChild(String type){
        Document output = render("<WORK><PAGE><L/><"+type.toUpperCase()+">a</"
        						 +type.toUpperCase()+"></PAGE></WORK>");
        Nodes pages = output.query("//"+DOC_PREFIX+":page", NS_MAP);
        Nodes child = output.query("//"+DOC_PREFIX+":"+type, NS_MAP);
        assumeTrue(pages.size() == 1 && child.size() == 1);
        assertThat(
        		type+" is a child of page",
        		child.get(0).getParent(),
        		is(pages.get(0))
        );
	}
	
    @Test
    public void moveCatchwordToPage() {
    	translatePageChild("cw");
    }
    @Test

    public void moveRunningTitleToPage() {
    	translatePageChild("rt");
    }
    @Test
    public void moveSignatureToPage() {
    	translatePageChild("sig");
    }
    @Test
    public void movePageNumberToPage() {
    	translatePageChild("pn");
    }
    
    private void checkFlatten(String str, String s1, String s2){
        Document output = render(str);
        Nodes n1 = output.query("//"+DOC_PREFIX+":"+s1, NS_MAP);
        Nodes n2 = output.query("//"+DOC_PREFIX+":"+s2, NS_MAP);
        Nodes lines = output.query("//"+DOC_PREFIX+":l",NS_MAP);
        assertTrue(n1.size()==1 && n2.size()==2);
        assertTrue(
        	"2x"+n2+" and 1x"+n1+"r are children of same  line",
        	n1.get(0).getParent() == lines.get(0) &&
        	n2.get(0).getParent() == lines.get(0) &&
        	n2.get(1).getParent() == lines.get(0)
        );
    }
    
    @Test
    public void flattenTypefaces() {
    	checkFlatten("<WORK><BLL>a<R>b</R>c</BLL></WORK>","r","bll");
    	checkFlatten("<WORK><R>a<BLL>b</BLL>c</R></WORK>","bll","r");
    }

    @Test
    public void flattenFontsize() {
        Document output = render("<WORK><FONT size=\"1\">a<FONT size=\"2\">b</FONT>c</FONT></WORK>");
        Nodes f1 = output.query("//"+DOC_PREFIX+":font[@size=1]", NS_MAP);
        Nodes f2 = output.query("//"+DOC_PREFIX+":font[@size=2]", NS_MAP);
        assertThat(
        	"3 fonts",
        	f1.size() + f2.size(),
        	is(3)
        );
        assertTrue(
        	"font 1 & 3 are of same size",
        	f1.size()==2 && f2.size()==1
        );
    }

    @Test
    public void splitSpeechesByLine() {
    	Document output = render("<WORK><S>a\nb\nc</S></WORK>");
        Nodes lines = output.query("//"+DOC_PREFIX+":l", NS_MAP);
        Nodes speeches = output.query("//"+DOC_PREFIX+":s", NS_MAP);
        assertTrue(lines.size()==3 && speeches.size()==3);
        assertTrue(
        	"3 speeches in 3 separate lines",
        	speeches.get(0).getParent() == lines.get(0) &&
        	speeches.get(1).getParent() == lines.get(1) &&
        	speeches.get(2).getParent() == lines.get(2)
        );
    	String i = ((Element)speeches.get(0)).getAttributeValue("n");
        assertTrue(
        	"all speeches have same index",
        	((Element)speeches.get(0)).getAttributeValue("n") == i &&
        	((Element)speeches.get(0)).getAttributeValue("n") == i
        );
    }

    @Test
    public void moveLabelOutOfLine() {
    	Document output = render("<WORK><BRACEGROUP>a <LABEL>b</LABEL> c\nd</BRACEGROUP></WORK>");
        Nodes lines = output.query("//"+DOC_PREFIX+":l", NS_MAP);
        Nodes label = output.query("//"+DOC_PREFIX+":label", NS_MAP);
        Nodes bg = output.query("//"+DOC_PREFIX+":bracegroup", NS_MAP);
        assertTrue(lines.size()==2 && label.size()==1 && bg.size()==1);
        assertThat(
        	"2 lines",
        	lines.size(),
        	is(2)
        );
        assertTrue(
        	"label is child of bracegroup",
        	label.get(0).getParent() == bg.get(0)
        );
    }
    
    @Test
    public void breakInlinesAroundMarg() {
    	checkFlatten("<WORK><I>a<MARG>b</MARG>c</I></WORK>","marg","i");
    }

}
