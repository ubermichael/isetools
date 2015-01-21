/*
 * Copyright (C) 2015 Michael Joyce <michael@negativespace.net>
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
package ca.nines.ise.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class CodePointTable {

  private final Map<String, CodePoint> table;

  public static class CodePointTableBuilder implements BuilderInterface<CodePointTable> {

	private Map<String, CodePoint> table;

	private CodePointTableBuilder() {
	  table = new HashMap<>();
	}

	@Override
	public CodePointTable build() {
	  return new CodePointTable(table);
	}

	public CodePointTableBuilder addCodePoint(CodePoint cp) {
	  table.put(cp.getName(), cp);
	  return this;
	}

	public CodePointTableBuilder from(InputStream in) throws IOException {
	  Reader reader = new InputStreamReader(in);
	  CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT, 0, 0);
	  for (CSVRecord record : csv) {
		try {
		  CodePoint cp = new CodePoint(record.get(0), Integer.parseInt(record.get(1)));
		  addCodePoint(cp);
		} catch (Exception e) {

		}
	  }
	  return this;
	}

  }

  private CodePointTable(Map<String, CodePoint> table) {
	this.table = table;
  }

  public static CodePointTableBuilder builder() {
	return new CodePointTableBuilder();
  }

  public static CodePointTable defaultCodePointTable() throws IOException {
	String loc = "/resources/data/entities.csv";
	InputStream in = CodePointTable.class.getResourceAsStream(loc);
	return builder().from(in).build();
  }

  public boolean hasCodePoint(String name) {
	return table.containsKey(name);
  }

  public CodePoint getCodePoint(String name) {
	if (table.containsKey(name)) {
	  return table.get(name);
	}
	return null;
  }

  public int size() {
	return table.size();
  }

  public String[] getCodePoints() {
	String names[] = table.keySet().toArray(new String[table.size()]);
	Arrays.sort(names);
	return names;
  }

}
