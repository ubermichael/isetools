package ca.nines.ise.writer;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;
import nu.xom.XPathContext;
import nu.xom.Document;
import org.junit.Before;
import org.xml.sax.SAXException;

import static org.junit.Assume.assumeThat;
import static org.junit.Assume.assumeNoException;
import static org.hamcrest.CoreMatchers.is;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class XMLWriterTestBase {
    public static final String DOC_PREFIX = "d";
    public static final String LINK_PREFIX = "i";
    public static final String HTML_PREFIX = "h";
    public static final XPathContext NS_MAP = new XPathContext();
    static {
        NS_MAP.addNamespace(DOC_PREFIX, XMLWriter.DOC_NS);
        NS_MAP.addNamespace(LINK_PREFIX, XMLWriter.LINK_NS);
        NS_MAP.addNamespace(HTML_PREFIX, XMLWriter.HTML_NS);
    }

    protected XMLWriter writer;

    @Before
    public void createWriter() throws ParserConfigurationException, UnsupportedEncodingException {
        writer = new XMLWriter();
    }

    protected Document render(String iml) throws SAXException, ParserConfigurationException, TransformerException {
        try {
            DOM d = new DOMBuilder(iml).build();
            assumeThat(d.getStatus(), is(DOM.DOMStatus.CLEAN));
            return writer.renderToXOM(d);
        } catch (IOException e) {
            assumeNoException(e);
            return null;
        }
    }
}
