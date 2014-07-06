/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;

/**
 * @author michael
 */
public class DOMStream {

  private ByteOrderMark bom;
  private String content;
  private String encoding;
  private ArrayList<String> lines;
  private final Log log = Log.getInstance();

  /**
   *
   * @param in
   * @param source <p>
   * @throws java.io.IOException
   */
  public DOMStream(InputStream in, String source) throws IOException {
    lines = new ArrayList<>();

    BOMInputStream bomStream = new BOMInputStream(in, ByteOrderMark.UTF_8, ByteOrderMark.UTF_32LE, ByteOrderMark.UTF_32BE, ByteOrderMark.UTF_16LE, ByteOrderMark.UTF_16BE);
    bom = bomStream.getBOM();
    encoding = "UTF-8";
    if (bom != null) {
      Message m = Message.builder("builder.bom").setSource(source).build();
      m.addNote("The byte order mark was " + bom.getCharsetName());
      log.add(m);
      encoding = bom.getCharsetName();
    }
    if (!encoding.equals("UTF-8")) {
      Message m = Message.builder("builder.notutf8").setSource(source).build();
      m.addNote("The incorrect encoding is " + encoding);
      log.add(m);
    }

    BufferedReader buffer = new BufferedReader(new InputStreamReader(bomStream, encoding));

    String line;
    StringBuilder sb = new StringBuilder();

    while ((line = buffer.readLine()) != null) {
      line = Normalizer.normalize(line, Form.NFKC);
      lines.add(line);
      sb.append(line).append("\n");
    }

    content = sb.toString().trim();
  }

  /**
   * Constructs a MessageBuilder from a string. The resulting DOM source will be
   * "#STRING".
   * <p>
   * @param input The string to parse.
   * <p>
   * @throws java.io.IOException
   */
  public DOMStream(String input) throws IOException {
    this(IOUtils.toInputStream(input, "UTF-8"), "#STRING");
  }

  /**
   * Constructs a MessageBuilder from a File. The resulting DOM source will return the
   * absolute path to the file.
   * <p>
   * @param input The file to read and parse.
   * <p>
   * @throws FileNotFoundException if the file cannot be found.
   * @throws IOException           if the file cannot be read.
   */
  public DOMStream(File input) throws FileNotFoundException, IOException {
    this(new FileInputStream(input), input.getCanonicalPath());
  }

  public ByteOrderMark getBOM() {
    return bom;
  }

  public String getContent() {
    return content;
  }

  public String getEncoding() {
    return encoding;
  }

  public String[] getLines() {
    return lines.toArray(new String[lines.size()]);
  }
}
