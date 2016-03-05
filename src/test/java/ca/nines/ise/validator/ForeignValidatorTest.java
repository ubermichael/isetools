package ca.nines.ise.validator;

import org.junit.Test;

public class ForeignValidatorTest extends ValidatorTestBase{
  
  ForeignValidator validator;
  
  public ForeignValidatorTest() throws Exception {
    validator = new ForeignValidator();
  }
  
  /**
   * Tests if @lang is a valid language code
   */
  @Test
  public void testValidLanguage() throws Exception {
    //should produce error(s)
    
    setUp();
    validator.validate(getDOM("<WORK><FOREIGN lang=\"notalanguage\">x</FOREIGN></WORK>"));
    checkLog(new String[]{"validator.foreign.invalidCode"});
    
    //should be fine
    
    setUp();
    validator.validate(getDOM("<WORK><FOREIGN lang=\"aaa\">x</FOREIGN></WORK>"));
    checkLog();
  }
  
  /**
   * Tests if @lang attribute exists
   */
  @Test
  public void testLanguageExists() throws Exception {
    //should produce error(s)
    
    setUp();
    validator.validate(getDOM("<WORK><FOREIGN>x</FOREIGN></WORK>"));
    checkLog(new String[]{"validator.foreign.missingCode"});
  }

}
