package ca.nines.ise.validator;

import org.junit.Test;

public class NestingValidatorTest extends ValidatorTestBase{
  
  NestingValidator validator;
  
  public NestingValidatorTest() throws Exception {
    validator = new NestingValidator(schema);
  }
  
  /**
   * Tests redundant tagging
   * @throws Exception
   */
  @Test
  public void testRedundant() throws Exception {
    //should produce an error
    setUp();
    validator.validate(getDOM("<WORK><em><em></em></em></WORK>"));
    checkLog(new String[]{"validator.nesting.redundant"});
    //should produce an error
    setUp();
    validator.validate(getDOM("<WORK><font size=\"4\">x<font size=\"4\">y</font></font></WORK>"));
    checkLog(new String[]{"validator.nesting.redundant"});
    //should produce an error
    setUp();
    validator.validate(getDOM("<WORK><foreign lang=\"a\">x<foreign lang=\"a\">y</foreign></foreign></WORK>"));
    checkLog(new String[]{"validator.nesting.redundant"});
    

    //should be fine
    setUp();
    validator.validate(getDOM("<WORK><s><s></s></s></WORK>"));
    checkLog();
    //should be fine
    setUp();
    validator.validate(getDOM("<WORK><font size=\"4\">x<font size=\"3\">y</font></font></WORK>"));
    checkLog();
    //should be fine
    setUp();
    validator.validate(getDOM("<WORK><foreign lang=\"a\">x<foreign lang=\"b\">y</foreign></foreign></WORK>"));
    checkLog();
  }  
  
  /**
   * Tests split tagging
   * @throws Exception
   */
  @Test
  public void testSplit() throws Exception {
    //should produce an error
    setUp();
    validator.validate(getDOM("<WORK><LD><CL></LD></CL></WORK>"));
    checkLog(new String[]{"validator.nesting.split"});
    //should produce 2 errors
    setUp();
    validator.validate(getDOM("<ACT><SCENE><MARG></ACT></SCENE></MARG>"));
    checkLog(new String[]{"validator.nesting.split", "validator.nesting.split"});
    //should be fine
    setUp();
    validator.validate(getDOM("<WORK><x><y></x></y></WORK>"));
    checkLog();
  }
  
  /**
   * Tests required parents
   * @throws Exception
   */
  
  public void testRequired() throws Exception {
    //should produce an error
    setUp();
    validator.validate(getDOM("<WORK><COL></COL></WORK>"));
    checkLog(new String[]{"validator.nesting.required"});
    //should produce an error
    setUp();
    validator.validate(getDOM("<WORK><COL><PAGE></PAGE></COL></WORK>"));
    checkLog(new String[]{"validator.nesting.required"});
    
    //should be fine
    setUp();
    validator.validate(getDOM("<WORK><PAGE><COL></COL></PAGE></WORK>"));
    checkLog();
    //should be fine
    setUp();
    validator.validate(getDOM("<WORK><PAGE><X><COL></COL></X></PAGE></WORK>"));
    checkLog();
  }
  
  

}
