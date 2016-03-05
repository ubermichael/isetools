package ca.nines.ise.validator;

import org.junit.Test;

public class HungWordValidatorTest extends ValidatorTestBase{
  
  HungWordValidator validator;
  
  public HungWordValidatorTest() throws Exception {
    validator = new HungWordValidator();
  }
  
  /**
   * Tests if HW is at the end of the line
   */
  @Test
  public void testEndOfLine() throws Exception {
    //should produce error(s)
    
    setUp();
    validator.validate(getDOM("<WORK>x<HW>x</HW>x</WORK>"));
    checkLog(new String[]{"validator.hungWord.endOfLine"});
    setUp();
    validator.validate(getDOM("<WORK>x<HW>x</HW><x/></WORK>"));
    checkLog(new String[]{"validator.hungWord.endOfLine"});
    setUp();
    validator.validate(getDOM("<WORK>x<HW>x</HW><x></x></WORK>"));
    checkLog(new String[]{"validator.hungWord.endOfLine"});

    //should be fine
    
    setUp();
    validator.validate(getDOM("<WORK>x<HW>x</HW>\n<HW>y</HW></WORK>"));
    checkLog();
  }

}
