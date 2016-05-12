package ca.nines.ise.validator;

import org.junit.Test;

public class TagBalanceValidatorTest extends ValidatorTestBase{
  
  TagBalanceValidator validator;
  
  public TagBalanceValidatorTest() throws Exception {
    validator = new TagBalanceValidator(schema);
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
    
    //should be no problems
    setUp();
    validator.validate(getDOM("<WORK><x></x></WORK>"));
    checkLog();
  }
  
  /**
   * Tests empty tag
   */
  @Test
  public void testEmpty() throws Exception {
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
    
    //should be no problems
    setUp();
    validator.validate(getDOM("<WORK><x></x></WORK>"));
    checkLog();
  }

}
