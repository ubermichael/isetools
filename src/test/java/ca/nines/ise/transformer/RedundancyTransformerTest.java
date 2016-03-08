package ca.nines.ise.transformer;

import java.io.IOException;

import org.junit.Test;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.NodeType;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RedundancyTransformerTest extends TransformerTestBase{
  
  RedundancyTransformer transformer;
  String[] redundantTags = {"c","cl","cw","em","i","j","ld","ls","pn","ra","rt","sc","sig","sp","title"};
  
  public RedundancyTransformerTest(){
    transformer = new RedundancyTransformer();
  }
  
  private void testRedundant(String tag, String att) throws IOException{
    DOM d = null;
    if (att != null)
      d = getDOM("<WORK><"+tag+" "+att+"=\"test\">x<"+tag+" "+att+"=\"test\">y</"+tag+">x</"+tag+"></WORK>");
    else
      d = getDOM("<WORK><"+tag+">x<"+tag+">y</"+tag+">x</"+tag+"></WORK>");
    
    d = transformer.transform(d);
    int tags = 0;
    for (Node n : d){
      if (n.type().equals(NodeType.START) && n.getName().toLowerCase().equals(tag.toLowerCase()))
        tags ++;
    }
    assertThat(
        "there is only one "+tag+" tag",
        tags,
        is(1)
    );
  }
  
  @Test
  public void testFont() throws IOException{
    testRedundant("font", "size");
    
    DOM d = transformer.transform(getDOM("<WORK><font size=\"4\">x<font size=\"5\">y</font>x</font></WORK>"));
    int tags = 0;
    for (Node n : d){
      if (n.type().equals(NodeType.START) && n.getName().toLowerCase().equals("font"))
        tags ++;
    }
    assertThat(
        "there are two font tags",
        tags,
        is(2)
    );
  }
  
  @Test
  public void testForeign() throws IOException{
    testRedundant("foreign","lang");
    

    DOM d = transformer.transform(getDOM("<WORK><foreign lang=\"aaa\">x<foreign lang=\"aab\">y</foreign>x</foreign></WORK>"));
    int tags = 0;
    for (Node n : d){
      if (n.type().equals(NodeType.START) && n.getName().toLowerCase().equals("foreign"))
        tags ++;
    }
    assertThat(
        "there are two foreign tag",
        tags,
        is(2)
    );
  }
  
  @Test
  public void testRest() throws IOException{
    for (String s : redundantTags){
      testRedundant(s, null);
    }
  }
}
