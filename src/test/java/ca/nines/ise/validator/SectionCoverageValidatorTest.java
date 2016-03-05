package ca.nines.ise.validator;

import org.junit.Test;

public class SectionCoverageValidatorTest extends ValidatorTestBase{
  
  SectionCoverageValidator validator;
  
  public SectionCoverageValidatorTest() throws Exception {
    validator = new SectionCoverageValidator();
  }
  
  /**
   * Tests if the text within frontmatter/backmatter are covered
   * @throws Exception
   */
  @Test
  public void testMatterCoverage() throws Exception {
    //should produce error(s)
    
    setUp();
    validator.validate(getDOM("<WORK><FRONTMATTER>x</FRONTMATTER></WORK>"));
    checkLog(new String[]{"validator.coverage.outside_div"});
    
    //should be okay
    
    setUp();
    validator.validate(getDOM("<WORK><FRONTMATTER><DIV>x</DIV></FRONTMATTER></WORK>"));
    checkLog();
    setUp();
    validator.validate(getDOM("<WORK><FRONTMATTER><DIV>x</DIV><DIV>y</DIV></FRONTMATTER></WORK>"));
    checkLog();
  }
  
  /**
   * Tests if the text in the body (outside matter) is covered
   * @throws Exception
   */
  @Test
  public void testBodyCoverage() throws Exception {
    //should produce error(s)
    setUp();
    validator.validate(getDOM("<WORK><ACT>x</ACT>x</WORK>"));
    checkLog(new String[]{"validator.coverage.outside_body"});
    setUp();
    validator.validate(getDOM("<WORK><DIV>x</DIV><ACT>x</ACT></WORK>"));
    checkLog(new String[]{"validator.coverage.outside_body","validator.coverage.outside_body"});
    setUp();
    validator.validate(getDOM("<WORK><DIV>x</DIV>x</WORK>"));
    checkLog(new String[]{"validator.coverage.outside_body"});
    //should be okay
    
    setUp();
    validator.validate(getDOM("<WORK><ACT>x</ACT></WORK>"));
    checkLog();
  }
  
  /**
   * Tests if the text within the document is within some kind of sectioning
   * @throws Exception
   */
  @Test
  public void testRequired() throws Exception {
    //should produce error(s)
    
    setUp();
    validator.validate(getDOM("<WORK>x</WORK>"));
    checkLog(new String[]{"validator.coverage.required"});
    
    //should be okay
    
    setUp();
    validator.validate(getDOM("<WORK><DIV>x</DIV></WORK>"));
    checkLog();
    setUp();
    validator.validate(getDOM("<WORK><DIV>x</DIV></WORK>"));
    checkLog();
    setUp();
    validator.validate(getDOM("<WORK><DIV><ACT>x</ACT></DIV></WORK>"));
    checkLog();
  }
  
  /**
   * Tests if pages cover all text (if a page exists)
   * @throws Exception
   */
  @Test
  public void testPageCoverage() throws Exception {
    //should produce error(s)
    
    setUp();
    validator.validate(getDOM("<WORK><PAGE></PAGE>x</WORK>"));
    checkLog(new String[]{"validator.coverage.outside_page"});
    setUp();
    validator.validate(getDOM("<WORK><FRONTMATTER><DIV>x</DIV></FRONTMATTER><PAGE>y</PAGE></WORK>"));
    checkLog(new String[]{"validator.coverage.outside_page"});
    
    //should be okay
    
    setUp();
    validator.validate(getDOM("<WORK><PAGE>x</PAGE></WORK>"));
    checkLog();
    setUp();
    validator.validate(getDOM("<WORK><PAGE>x</PAGE><PAGE>y</PAGE></WORK>"));
    checkLog();
    setUp();
    validator.validate(getDOM("<WORK><PAGE><FRONTMATTER><DIV>x</DIV></FRONTMATTER>y</PAGE></WORK>"));
    checkLog();
  }
  
}
