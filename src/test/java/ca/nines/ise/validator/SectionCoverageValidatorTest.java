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
  }
  
}
