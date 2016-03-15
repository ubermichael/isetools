package ca.nines.ise.transformer;

import java.io.IOException;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assume.assumeThat;

public class TransformerTestBase {
  public DOM getDOM(String iml) throws IOException{
    DOM d = new DOMBuilder(iml).build();
    assumeThat(d.getStatus(), is(DOM.DOMStatus.CLEAN));
    return d;
  }
}
