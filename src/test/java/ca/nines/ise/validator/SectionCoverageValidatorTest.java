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
    setUp();
    validator.validate(getDOM("<WORK><FRONTMATTER>x</FRONTMATTER></WORK>"));
    checkLog(new String[]{"validator.coverage.outside_div"});
    
    setUp();
    validator.validate(getDOM("<WORK><FRONTMATTER><DIV>x</DIV></FRONTMATTER></WORK>"));
    checkLog();
  }
  
  /**
   * Tests if the text in the body (outside matter) is covered by DIV and ACT
   * @throws Exception
   */
  @Test
  public void testBodyCoverage() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><DIV>x</DIV><ACT>x</ACT></WORK>"));
    checkLog(new String[]{"validator.coverage.outside_body","validator.coverage.outside_body"});
    
    setUp();
    validator.validate(getDOM("<WORK><DIV><ACT>x</ACT></DIV></WORK>"));
    checkLog();
  }
  
  /**
   * Tests if the text in the body (outside matter) is covered by ACT
   * @throws Exception
   */
  @Test
  public void testBodyActCoverage() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><ACT>x</ACT>x</WORK>"));
    checkLog(new String[]{"validator.coverage.outside_body"});
    
    setUp();
    validator.validate(getDOM("<WORK><ACT>x</ACT></WORK>"));
    checkLog();
  }

  /**
   * Tests if the text in the body (outside matter) is covered by DIV
   * @throws Exception
   */
  @Test
  public void testBodyDivCoverage() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><DIV>x</DIV>x</WORK>"));
    checkLog(new String[]{"validator.coverage.outside_body"});

    setUp();
    validator.validate(getDOM("<WORK><DIV>x</DIV></WORK>"));
    checkLog();
  }
  
  /**
   * Tests if the text within the document is within some kind of sectioning
   * @throws Exception
   */
  @Test
  public void testRequired() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK>x</WORK>"));
    checkLog(new String[]{"validator.coverage.required"});
  }
  
  /**
   * Tests if pages cover all text (if a page exists)
   * @throws Exception
   */
  @Test
  public void testPageCoverage() throws Exception {
    setUp();
    validator.validate(getDOM("<WORK><PAGE></PAGE>x</WORK>"));
    checkLog(new String[]{"validator.coverage.outside_page"});
    
    setUp();
    validator.validate(getDOM("<WORK><PAGE>x</PAGE></WORK>"));
    checkLog();
  }
}
