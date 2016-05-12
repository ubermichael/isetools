package ca.nines.ise.validator;

import org.junit.Test;

public class DescendantCountValidatorTest extends ValidatorTestBase{
  
  DescendantCountValidator validator;
  
  public DescendantCountValidatorTest() throws Exception{
    validator = new DescendantCountValidator();
  }
  
  /**
   * Test siblings
   * @throws Exception
   */
  @Test
  public void testOverCount() throws Exception {
    //should produce an error
    setUp();
    validator.validate(getDOM("<WORK><s><sp>x</sp><sp>y</sp></s></WORK>"));
    checkLog(new String[]{"validator.descendantCount.overCount"});
    
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
    //should error
    setUp();
    validator.validate(getDOM("<WORK><s><sp>x</sp><y><sp>y</sp></y></s></WORK>"));
    checkLog(new String[]{"validator.descendantCount.overCount"});
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
