package ca.nines.ise.transformer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.Node;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DeprecatedTransformerTest extends TransformerTestBase{
  
  DeprecatedTransformer transformer;
  //<old,new>
  static final Map<String , String> TAG_MAP = new HashMap<String , String>() {{
    put("blockquote", "quote");
    put("iseheader", null);
    put("meta", null);
    put("link", null);
    put("titlehead", "title");
    put("fontgroup", "font");
    put("h1", "ld");
    put("h2", "ld");
    put("h3", "ld");
    put("h4", "ld");
    put("h5", "ld");
    put("h6", "ld");
    put("poem", "div");
    put("section", "div");
   }};
   
   //<tag,<old att name,new att name>>
   static final Map<String , HashMap<String,String>> ATTRIBUTE_MAP = new HashMap<String , HashMap<String,String>>() {{
     put("font", new HashMap<String , String>() {{put("n","size");}});
     put("linegroup", new HashMap<String , String>() {{put("form","t");}});
     put("space", new HashMap<String , String>() {{put("n","l");}});
     put("indent", new HashMap<String , String>() {{put("n","l");}});
     put("div", new HashMap<String , String>() {{put("n","name");}});
    }};
  
  public DeprecatedTransformerTest(){
    transformer = new DeprecatedTransformer();
  }
  
  @Test
  public void testDeprecated() throws IOException{
    for (String n : TAG_MAP.keySet()){
      DOM d;
      if (n.equals("meta") ||  n.equals("link"))
        d = transformer.transform(getDOM("<WORK><"+n+"/></WORK>"));
      else
        d = transformer.transform(getDOM("<WORK><"+n+">x</"+n+"></WORK>"));
      
      if (TAG_MAP.get(n) == null)
        assertTrue(
            n+" is removed",
            d.find(n.toUpperCase()) == null
        );
      else
        assertTrue(
            n+" is transformed to "+TAG_MAP.get(n),
            d.find(TAG_MAP.get(n).toUpperCase()) != null
        );
      
    }
    
    //test attributes
    
    
  }

}
