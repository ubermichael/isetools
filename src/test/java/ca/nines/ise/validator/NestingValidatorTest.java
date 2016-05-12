package ca.nines.ise.validator;

import org.junit.Test;

public class NestingValidatorTest extends ValidatorTestBase{
  
  NestingValidator validator;
  
  public NestingValidatorTest() throws Exception {
    validator = new NestingValidator(schema);
  }
  

  /**
   * Tests redundant font tags
   * @throws Exception
   */
  @Test
  public void testRedundantFont() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><font size=\"4\">x<font size=\"4\">y</font></font></WORK>"));
    checkLog(new String[]{"validator.nesting.redundant"}); 

    setUp();
    validator.validate(getDOM("<WORK><font size=\"4\">x<font size=\"3\">y</font></font></WORK>"));
    checkLog();
  }
  
  /**
   * Tests redundant foreign tags
   * @throws Exception
   */
  @Test
  public void testRedundantForeign() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><foreign lang=\"a\">x<foreign lang=\"a\">y</foreign></foreign></WORK>"));
    checkLog(new String[]{"validator.nesting.redundant"});

    setUp();
    validator.validate(getDOM("<WORK><foreign lang=\"a\">x<foreign lang=\"b\">y</foreign></foreign></WORK>"));
    checkLog();
  }
  
  /**
   * Tests other redundant tags
   * @throws Exception
   */
  @Test
  public void testRedundant() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><em><em></em></em></WORK>"));
    checkLog(new String[]{"validator.nesting.redundant"});
    
    setUp();
    validator.validate(getDOM("<WORK><s><s></s></s></WORK>"));
    checkLog();
  }  
  
  /**
   * Tests split tagging
   * @throws Exception
   */
  @Test
  public void testSplit() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><LD><CL></LD></CL></WORK>"));
    checkLog(new String[]{"validator.nesting.split"});
    
    setUp();
    validator.validate(getDOM("<WORK><X><Y></X></Y></WORK>"));
    checkLog();
  }
  
  /**
   * Tests that two tags that split each other produce two errors (one each)
   * @throws Exception
   */
  @Test
  public void testSplitEachOther() throws Exception {
    setUp();
    validator.validate(getDOM("<ACT><SCENE><MARG></ACT></SCENE></MARG>"));
    checkLog(new String[]{"validator.nesting.split", "validator.nesting.split"});
  }
  
  /**
   * Tests required parents
   * @throws Exception
   */
  public void testRequired() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><COL></COL></WORK>"));
    checkLog(new String[]{"validator.nesting.required"});
    
    setUp();
    validator.validate(getDOM("<WORK><PAGE><COL></COL></PAGE></WORK>"));
    checkLog();
  }
  
  /**
   * Tests required ancestors
   * @throws Exception
   */
  public void testRequiredGrandchild() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><PAGE><X><COL></COL></X></PAGE></WORK>"));
    checkLog();
  }
  
  
  

}
