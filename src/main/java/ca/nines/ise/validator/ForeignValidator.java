/*
 * Copyright (C) 2015 Malcolm Moran <malcolm.moran@outlook.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package ca.nines.ise.validator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import ca.nines.ise.annotation.ErrorCode;
import ca.nines.ise.dom.DOM;
import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.NodeType;
import ca.nines.ise.node.StartNode;
/**
 * Ensures FOREIGN tags have valid @lang attributes, with respect to ISO 639-1/3
 * 
 * @author Malcolm Moran <malcolm.moran@outlook.com>
 */
public class ForeignValidator {
  private final List<String> langCodes;
  
  public ForeignValidator(){
    langCodes = new ArrayList<String>();
    try {
      URL url = getClass().getResource("/data/iso-639-3_Language_Name_Index.tab");
      File file = new File(url.getPath());
      CSVParser parser = CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.TDF.withHeader());
      
      for (CSVRecord record : parser){
        langCodes.add(record.get(0));
      }
      
    } catch (IOException e) {
        e.printStackTrace();
    } 
  }
  
  @ErrorCode(code = {
    "validator.foreign.invalidCode",
    "validator.foreign.missingCode"
  })
  private void process_start(StartNode n) {
    if (n.hasAttribute("lang")){
      if (!langCodes.contains(n.getAttribute("lang"))){
        Message m = Message.builder("validator.foreign.invalidCode")
            .fromNode(n)
            .addNote("Tag " + n.getName() + " has an invalid @lang attribute")
            .addNote("@lang must contain a valid ISO 639-1 or 639-3 language code")
            .build();
        Log.addMessage(m);
      }
    }else{
      Message m = Message.builder("validator.foreign.missingCode")
          .fromNode(n)
          .addNote("Tag " + n.getName() + " is missing mandatory @lang attribute")
          .addNote("@lang must contain a valid ISO 639-1 or 639-3 language code")
          .build();
      Log.addMessage(m);
    }
  }

  public void validate(DOM dom) {
    for (Node n : dom) {
      if (n.getName().toLowerCase().equals("foreign") &&
          n.type() == NodeType.START){
            process_start((StartNode) n);
      }
    }
  }

}