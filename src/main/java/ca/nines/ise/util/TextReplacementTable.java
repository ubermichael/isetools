/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *

 */
public class TextReplacementTable {
    
    private final Map<String, String> table;

    public static class TextReplacementTableBuilder implements BuilderInterface<TextReplacementTable> {
        
        private Map<String, String> table;
        
        private TextReplacementTableBuilder() {
            table = new HashMap<>();            
        }
        
        public TextReplacementTable build() {
            return new TextReplacementTable(table);
        }
        
        public TextReplacementTableBuilder addEntry(String src, String dst) {
            table.put(src, dst);
            return this;
        }
        
        public TextReplacementTableBuilder from(InputStream in) throws IOException {
            Reader reader = new InputStreamReader(in);
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT, 0, 0);
            for(CSVRecord record : csv) {
                if (record.get(1).equals("destination")) {
                    continue;
                }
                addEntry(record.get(0), record.get(1));
            }
            return this;
        }
    }
    
    private TextReplacementTable(Map<String, String> table) {
        this.table = table;
    }
    
    public static TextReplacementTableBuilder builder() {
        return new TextReplacementTableBuilder();
    }
    
    public static TextReplacementTable defaultTextReplacementTable() throws IOException {
        String loc = "/data/text-replacements.csv";
        InputStream in = TextReplacementTable.class.getResourceAsStream(loc);
        if(in == null) {
            throw new IOException("Cannot find " + loc + " as a resource.");            
        }
        return builder().from(in).build();
    }
    
    public String getReplacement(String src) {
        return table.get(src);
    }
    
    public String[] getSources() {
        String sources[] = table.keySet().toArray(new String[table.size()]);
        Arrays.sort(sources);
        return sources;
    }
    
}
