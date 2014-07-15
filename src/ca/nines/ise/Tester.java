/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.dom.DOMBuilder;
import ca.nines.ise.log.Log;
import ca.nines.ise.output.Output;
import ca.nines.ise.output.RTFOutput;

import com.lowagie.text.*;
import com.lowagie.text.rtf.*;

/**
 *
 * @author michael
 */
public class Tester {

  public static void main(String[] args) {
    Log log = Log.getInstance();
    try {
      DOM dom = new DOMBuilder("<ACT n=\"1\">\n"
              + "<SCENE n=\"1\">\n"
              + "\n"
              + "<TLN n=\"1\"/><LD>1.1</LD>\n"
              + "\n"
              + "<L n=\"0.1\"/><TLN n=\"2\"/><SD t=\"sound, entrance\">[Music.] Enter Orsino Duke of Illyria, Curio, and other <TLN n=\"3\"/>Lords.</SD>\n"
              + "\n"
              + "<L n=\"1\"/><TLN n=\"4\"/><S><SP norm=\"Orsino\">Orsino</SP>\n"
              + "<MODE t=\"verse\">If music be the food of love, play on,\n"
              + "<L n=\"2\"/><TLN n=\"6\"/> Give me excess of it, that surfeiting,\n"
              + "<L n=\"3\"/><TLN n=\"7\"/> The appetite may sicken, and so die.\n"
              + "<L n=\"4\"/><TLN n=\"8\"/><SD t=\"whoto, optional\">[To the Musicians]</SD> That strain again! It had a dying fall; \n"
              + "<L n=\"5\"/><TLN n=\"9\"/>Oh, it came o'er my ear like the sweet sound\n"
              + "<L n=\"6\"/><TLN n=\"10\"/>That breathes upon a bank of violets,\n"
              + "<L n=\"7\"/><TLN n=\"11\"/>Stealing, and giving odor. <SD t=\"whoto\">[To the Musicians]</SD> Enough, no more.\n"
              + "<L n=\"8\"/><TLN n=\"12\"/>'Tis not so sweet now as it was before.\n"
              + "<L n=\"9\"/><TLN n=\"13\"/>O spirit of love, how quick and fresh art thou,\n"
              + "<L n=\"10\"/><TLN n=\"14\"/>That notwithstanding thy capacity\n"
              + "<L n=\"11\"/><TLN n=\"15\"/>Receiveth as the sea, nought enters there,\n"
              + "<L n=\"12\"/><TLN n=\"16\"/>Of what validity and pitch soe'er,\n"
              + "<L n=\"13\"/><TLN n=\"17\"/>But falls into abatement and low price\n"
              + "<L n=\"14\"/><TLN n=\"18\"/>Even in a minute. So full of shapes is fancy,\n"
              + "<L n=\"15\"/><TLN n=\"19\"/>That it alone is high fantastical.</S>\n"
              + "\n"
              + "<L n=\"16\" part=\"i\"/><TLN n=\"20\"/><S><SP norm=\"Curio\">Curio</SP>\n"
              + "Will you go hunt, my Lord?</S>\n"
              + "<L n=\"16\" part=\"m\"/><TLN n=\"21\"/><S><SP norm=\"Orsino\">Orsino</SP>\n"
              + "What, Curio?</S>\n"
              + "\n"
              + "<L n=\"16\" part=\"f\"/><TLN n=\"22\"/><S><SP norm=\"Curio\">Curio</SP>\n"
              + "The hart.</S>\n"
              + "\n"
              + "<L n=\"17\"/><TLN n=\"23\"/><S><SP norm=\"Orsino\">Orsino</SP>\n"
              + "Why so I do, the noblest that I have.\n"
              + "<L n=\"18\"/><TLN n=\"24\"/>O when mine eyes did see Olivia first,\n"
              + "<L n=\"19\"/><TLN n=\"25\"/>Methought she purged the air of pestilence;\n"
              + "<L n=\"20\"/><TLN n=\"26\"/>That instant was I turned into a hart,\n"
              + "<L n=\"21\"/><TLN n=\"27\"/>And my desires, like fell and cruel hounds,\n"
              + "<L n=\"22\" part=\"i\"/><TLN n=\"28\"/>E'er since pursue me.\n"
              + "\n"
              + "<L n=\"\"/><TLN n=\"29\"/><SD t=\"entrance\">Enter Valentine.</SD>\n"
              + "\n"
              + "<L n=\"22\" part=\"f\"/>How now, what news from her?</S>\n"
              + "\n"
              + "<L n=\"23\"/><TLN n=\"30\"/><S><SP norm=\"Valentine\">Valentine</SP>\n"
              + "So please my lord, I might not be admitted,\n"
              + "<L n=\"24\"/><TLN n=\"31\"/>But from her handmaid do return this answer:\n"
              + "<L n=\"25\"/><TLN n=\"32\"/>The element itself, till seven years' heat,\n"
              + "<L n=\"26\"/><TLN n=\"33\"/>Shall not behold her face at ample view;\n"
              + "<L n=\"27\"/><TLN n=\"34\"/>But like a cloistress she will veil{`e}d walk,\n"
              + "<L n=\"28\"/><TLN n=\"35\"/>And water once a day her chamber round\n"
              + "<L n=\"29\"/><TLN n=\"36\"/>With eye-offending brine--all this to season\n"
              + "<L n=\"30\"/><TLN n=\"37\"/>A brother's dead love, which she would keep fresh\n"
              + "<L n=\"31\"/><TLN n=\"38\"/>And lasting in her sad remembrance.</S>").build();
      Output renderer = new RTFOutput();
      renderer.render(dom);
    } catch (Exception ex) {
      ex.printStackTrace(System.err);
    } finally {
      System.out.println(log);
    }
    System.out.println(log);
  }
}
