package ca.nines.ise.validator;

import org.junit.Test;
import static org.junit.Assume.assumeThat;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;

import ca.nines.ise.validator.node.TestBase;

public class SiblingCountValidatorTest extends TestBase{
  
  SiblingCountValidator validator;
  
  public SiblingCountValidatorTest(){
    validator = new SiblingCountValidator();
  }
  
  private DOM getDOM(String iml) throws IOException{
    DOM d = new DOMBuilder(iml).build();
    assumeThat(d.getStatus(), is(DOM.DOMStatus.CLEAN));
    return d;
  }
  
  /**
   * Test direct siblings
   * @throws Exception
   */
  @Test
  public void testOverCount() throws Exception {
    //should produce an error
    setUp();
    validator.validate(getDOM("<WORK><s><sp>x</sp><sp>y</sp></s></WORK>"));
    checkLog(new String[]{"validator.siblingCount.overCount"});
    
    //should be no problems
    setUp();
    validator.validate(getDOM("<WORK><s><sp>x</sp></s></WORK>"));
    checkLog();
    
    //should be no problems
    setUp();
    validator.validate(getDOM("<WORK><s><x>x</x><x>y</x></s></WORK>"));
    checkLog();
  }
  
  /**
   * Test descendants, not siblings
   */
  @Test
  public void testAllDescendants() throws Exception {
    //should be no problems
    setUp();
    validator.validate(getDOM("<WORK><s><sp>x</sp><y><sp>y</sp></y></s></WORK>"));
    checkLog();
  }
  
  /**
   * Test cousins
   */
  @Test
  public void testCousins() throws Exception {
    //should be no problems
    setUp();
    validator.validate(getDOM("<WORK><s><sp>x</sp></s><s><sp>y</sp></s></WORK>"));
    checkLog();
  }
}
