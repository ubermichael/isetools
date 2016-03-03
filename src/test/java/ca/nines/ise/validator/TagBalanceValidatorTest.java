package ca.nines.ise.validator;

import org.junit.Test;
import static org.junit.Assume.assumeThat;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;

import ca.nines.ise.validator.node.TestBase;

public class TagBalanceValidatorTest extends TestBase{
  
  TagBalanceValidator validator;
  
  public TagBalanceValidatorTest(){
    validator = new TagBalanceValidator();
  }
  
  private DOM getDOM(String iml) throws IOException{
    DOM d = new DOMBuilder(iml).build();
    assumeThat(d.getStatus(), is(DOM.DOMStatus.CLEAN));
    return d;
  }
  
  /**
   * Tests if a start tag is missing
   * @throws Exception
   */
  @Test
  public void testMissingStart() throws Exception {
    //should produce an error
    setUp();
    validator.validate(getDOM("<WORK></x></WORK>"));
    checkLog(new String[]{"validator.tagBalance.missing_start_tag"});
    
    //should produce an error
    setUp();
    validator.validate(getDOM("<WORK><x></x></x></WORK>"));
    checkLog(new String[]{"validator.tagBalance.missing_start_tag"});
    
    //should be no problems
    setUp();
    validator.validate(getDOM("<WORK><x></x></WORK>"));
    checkLog();
    
    //should be no problems
    setUp();
    validator.validate(getDOM("<WORK><x/></WORK>"));
    checkLog();
  }
  
  /**
   * Tests if an end tag is missing
   * @throws Exception
   */
  @Test
  public void testMissingEnd() throws Exception {
    //should produce an error
    setUp();
    validator.validate(getDOM("<WORK><x></WORK>"));
    checkLog(new String[]{"validator.tagBalance.unclosed"});
    
    //should produce an error
    setUp();
    validator.validate(getDOM("<WORK><x><x></x></WORK>"));
    checkLog(new String[]{"validator.tagBalance.unclosed"});
    
    //should be no problems
    setUp();
    validator.validate(getDOM("<WORK><x></x></WORK>"));
    checkLog();
    
    //should be no problems
    setUp();
    validator.validate(getDOM("<WORK><x/></WORK>"));
    checkLog();
  }

}
