package ca.nines.ise.validator;

import org.junit.Test;

public class SpanLineValidatorTest extends ValidatorTestBase{
  
  SpanLineValidator validator;
  
  public SpanLineValidatorTest() throws Exception {
    validator = new SpanLineValidator();
  }
  
  /**
   * Tests if tag spanned multiple lines
   * @throws Exception
   */
  @Test
  public void testSpannedLines() throws Exception {
    //should produce error(s)
    
    setUp();
    validator.validate(getDOM("<WORK><ORNAMENT>\n</ORNAMENT></WORK>"));
    checkLog(new String[]{"validator.spanLine.spannedLines"});
    
    //should be fine
    
    setUp();
    validator.validate(getDOM("<WORK>\n<ORNAMENT>x</ORNAMENT>\n</WORK>"));
    checkLog();
  }
  
}
