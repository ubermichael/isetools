package ca.nines.ise.validator;

import org.junit.Test;

public class SplitLineValidatorTest extends ValidatorTestBase{
  
  SplitLineValidator validator;
  
  public SplitLineValidatorTest() throws Exception {
    validator = new SplitLineValidator();
  }
  
  /**
   * Tests if the part="m" is present but a split line hasn't been started
   * @throws Exception
   */
  @Test
  public void testSplitM() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><L part=\"m\"/></WORK>"));
    checkLog(new String[]{"validator.splitLine.notStartedM"});
    
    setUp();
    validator.validate(getDOM("<WORK><L part=\"i\"/><L part=\"m\"/><L part=\"f\"/></WORK>"));
    checkLog();
  }
  
  /**
   * Tests if the part="f" is present but a split line hasn't been started
   * @throws Exception
   */
  @Test
  public void testSplitF() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><L part=\"f\"/></WORK>"));
    checkLog(new String[]{"validator.splitLine.notStartedF"});
    
    setUp();
    validator.validate(getDOM("<WORK><L part=\"i\"/><L part=\"f\"/></WORK>"));
    checkLog();  
    }
  
  /**
   * Tests if the part line wasn't ended
   * @throws Exception
   */
  @Test
  public void testSplitUnclosed() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><L part=\"i\"/></WORK>"));
    checkLog(new String[]{"validator.splitLine.unclosed"});
  }
  
  /**
   * Tests if invalid
   * @throws Exception
   */
  @Test
  public void testSplitValid() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><L part=\"i\"/><L part=\"i\"/><L part=\"f\"/><L part=\"f\"/></WORK>"));
    checkLog(new String[]{"validator.splitLine.invalid"});
    
    setUp();
    validator.validate(getDOM("<WORK><L part=\"i\"/><x/><L part=\"f\"/></WORK>"));
    checkLog();
  }
  
  /**
   * tests if sectioning started before is splitting
   * @throws Exception
   */
  @Test
  public void testSplitBefore() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><DIV><L part=\"i\"/></DIV><L part=\"f\"/></WORK>"));
    checkLog(new String[]{"validator.partLine.crossing"});
    
    setUp();
    validator.validate(getDOM("<WORK><DIV><L part=\"i\"/><L part=\"f\"/></DIV></WORK>"));
    checkLog();
  }
  


  /**
   * tests if sectioning started during is splitting
   * @throws Exception
   */
  @Test
  public void testSplitDuring() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><L part=\"i\"/><DIV><L part=\"f\"/></DIV></WORK>"));
    checkLog(new String[]{"validator.partLine.crossing"});
    
    setUp();
    validator.validate(getDOM("<WORK><L part=\"i\"/><DIV></DIV><L part=\"f\"/></WORK>"));
    checkLog();
  }
}
