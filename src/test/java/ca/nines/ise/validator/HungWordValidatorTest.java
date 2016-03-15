package ca.nines.ise.validator;

import org.junit.Test;

public class HungWordValidatorTest extends ValidatorTestBase{
  
  HungWordValidator validator;
  
  public HungWordValidatorTest() throws Exception {
    validator = new HungWordValidator();
  }
  
  /**
   * Tests if HW followed by text
   */
  @Test
  public void testHWTextFollowing() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK>x<HW>x</HW>x</WORK>"));
    checkLog(new String[]{"validator.hungWord.endOfLine"});
  }
  
  
  /**
   * Tests if HW is valid
   */
  @Test
  public void testHWValid() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK>x<HW>x</HW>\n<HW>y</HW></WORK>"));
    checkLog();
  }
  

  /**
   * Tests if HW is followed by an emtpy tag
   */
  @Test
  public void testHWEmptyFollowing() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK>x<HW>x</HW><x/></WORK>"));
    checkLog(new String[]{"validator.hungWord.endOfLine"});
  }

  /**
   * Tests if HW is followed by a start tag
   */
  @Test
  public void testHWStartFollowing() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK>x<HW>x</HW><x>\n</x></WORK>"));
    checkLog(new String[]{"validator.hungWord.endOfLine"});
  }

}
