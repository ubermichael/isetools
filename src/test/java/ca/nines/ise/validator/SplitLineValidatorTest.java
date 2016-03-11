package ca.nines.ise.validator;

import org.junit.Test;

public class SplitLineValidatorTest extends ValidatorTestBase{
  
  SplitLineValidator validator;
  
  public SplitLineValidatorTest() throws Exception {
    validator = new SplitLineValidator();
  }
  
  /**
   * Tests if split line is malformed
   * @throws Exception
   */
  @Test
  public void testSplitStructure() throws Exception {
    //should produce error(s)
    
    setUp();
    validator.validate(getDOM("<WORK><L part=\"i\"/><L part=\"i\"/><L part=\"f\"/><L part=\"f\"/></WORK>"));
    checkLog(new String[]{"validator.splitLine.recursive"});
    setUp();
    validator.validate(getDOM("<WORK><L part=\"m\"/></WORK>"));
    checkLog(new String[]{"validator.splitLine.notStartedM"});
    setUp();
    validator.validate(getDOM("<WORK><L part=\"f\"/></WORK>"));
    checkLog(new String[]{"validator.splitLine.notStartedF"});
    setUp();
    validator.validate(getDOM("<WORK><L part=\"i\"/></WORK>"));
    checkLog(new String[]{"validator.splitLine.unclosed"});
    /*setUp();
    validator.validate(getDOM("<WORK><L part=\"i\"/><L/><L part=\"f\"/></WORK>"));
    checkLog(new String[]{"validator.splitLine.missingPart"});
    */
    //should be okay
    
    setUp();
    validator.validate(getDOM("<WORK><L part=\"i\"/><L part=\"m\"/><L part=\"f\"/></WORK>"));
    checkLog();
    setUp();
    validator.validate(getDOM("<WORK><L part=\"i\"/><L part=\"f\"/></WORK>"));
    checkLog();
    setUp();
    validator.validate(getDOM("<WORK><L part=\"i\"/><x/><L part=\"f\"/></WORK>"));
    checkLog();
    
  }
  
  /**
   * Tests if sectioning tags are splitting split lines
   * @throws Exception
   */
  @Test
  public void testSplitSectioning() throws Exception {
    //should produce error(s)
    
    setUp();
    validator.validate(getDOM("<WORK><L part=\"i\"/><DIV><L part=\"f\"/></DIV></WORK>"));
    checkLog(new String[]{"validator.partLine.crossing"});
    setUp();
    validator.validate(getDOM("<WORK><DIV><L part=\"i\"/></DIV><L part=\"f\"/></WORK>"));
    checkLog(new String[]{"validator.partLine.crossing"});
    
    //should be okay
    
    setUp();
    validator.validate(getDOM("<WORK><L part=\"i\"/><DIV></DIV><L part=\"f\"/></WORK>"));
    checkLog();
    setUp();
    validator.validate(getDOM("<WORK><DIV><L part=\"i\"/><L part=\"f\"/></DIV></WORK>"));
    checkLog();
    setUp();
    validator.validate(getDOM("<WORK><DIV><L part=\"i\"/><DIV></DIV><L part=\"f\"/></DIV></WORK>"));
    checkLog();
  }

}
