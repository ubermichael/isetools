/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
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
package ca.nines.ise.dom;

import ca.nines.ise.log.Log;
import ca.nines.ise.log.Message;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;

/**
 * DOMStream determines an input stream's encoding and throws an error if it is
 * not UTF-8. It will also correct any typographic quotes to straight quotes,
 * and normalizes the input to NFKC.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class DOMStream {

  private final ByteOrderMark bom;
  private final String content;
  private final String encoding;
  private final ArrayList<String> lines;

  /**
   * Construct a DOMStream from an input stream and record the source of the
   * input data.
   *
   * @param in
   * @param source
   * @throws java.io.IOException
   */
  public DOMStream(InputStream in, String source) throws IOException {
    lines = new ArrayList<>();
    boolean warnedSmartQuotes = false;

    BOMInputStream bomStream = new BOMInputStream(in, ByteOrderMark.UTF_8, ByteOrderMark.UTF_32LE, ByteOrderMark.UTF_32BE, ByteOrderMark.UTF_16LE, ByteOrderMark.UTF_16BE);
    bom = bomStream.getBOM();
    if (bom != null) {
      Message m = Message.builder("builder.bom")
              .setSource(source)
              .addNote("The byte order mark was " + bom.getCharsetName())
              .build();
      Log.addMessage(m);
      encoding = bom.getCharsetName();
    } else {
      encoding = "UTF-8";
    }

    if (!encoding.equals("UTF-8")) {
      Message m = Message.builder("builder.notutf8")
              .setSource(source)
              .addNote("The incorrect encoding is " + encoding)
              .build();
      Log.addMessage(m);
    }

    BufferedReader buffer = new BufferedReader(new InputStreamReader(bomStream, encoding));
    String line;
    StringBuilder sb = new StringBuilder();

    Pattern p = Pattern.compile("\u201C|\u201D");

    while ((line = buffer.readLine()) != null) {
      line = Normalizer.normalize(line, Form.NFKC);
      Matcher m = p.matcher(line);
      if (m.find()) {
        line = m.replaceAll("\"");
        if (!warnedSmartQuotes) {
          warnedSmartQuotes = true;
          Message msg = Message.builder("builder.smartquotes")
                  .setSource(source)
                  .addNote("The first occurence of smart quotes was at line " + lines.size())
                  .build();
          Log.addMessage(msg);
        }
      }
      lines.add(line);
      sb.append(line).append("\n");
    }

    content = sb.toString().trim();
  }

  /**
   * Constructs a DOMString from a string. The resulting DOM source will be
   * "#STRING".
   *
   * @param input The string to parse.
   *
   * @throws java.io.IOException
   */
  public DOMStream(String input) throws IOException {
    this(IOUtils.toInputStream(input, "UTF-8"), "#STRING");
  }

  /**
   * Constructs a DOMStream from a File. The resulting DOM source will
   * return the absolute path to the file.
   *
   * @param input The file to read and parse.
   *
   * @throws FileNotFoundException if the file cannot be found.
   * @throws IOException if the file cannot be read.
   */
  public DOMStream(File input) throws FileNotFoundException, IOException {
    this(new FileInputStream(input), input.getCanonicalPath());
  }

  /**
   * Return the byte order mark, if there is one.
   *
   * @return ByteOrderMark
   */
  public ByteOrderMark getBOM() {
    return bom;
  }

  /**
   * Return the processed content of the file.
   *
   * @return String
   */
  public String getContent() {
    return content;
  }

  /**
   * Return the file's encoding.
   *
   * @return String
   */
  public String getEncoding() {
    return encoding;
  }

  /**
   * Return the list of lines found in the input.
   *
   * @return String[]
   */
  public String[] getLines() {
    return lines.toArray(new String[lines.size()]);
  }
}
