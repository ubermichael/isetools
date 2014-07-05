/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;
import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 *
 * @author michael
 */
public class FileFinder extends DirectoryWalker<File> {

  FileFilter filter;

    public ArrayList<File> find(File startDirectory, String wildcard) throws IOException {
      ArrayList<File> files = new ArrayList<>();
      filter = new WildcardFileFilter(wildcard);
      walk(startDirectory, files);
      return files;
    }

    public ArrayList<File> find(File startDirectory, Pattern matcher) throws IOException {
      ArrayList<File> files = new ArrayList<>();
      filter = new RegexFileFilter(matcher);
      walk(startDirectory, files);
      return files;
    }

    @Override
  protected boolean handleDirectory(File directory, int depth, Collection<File> results) throws IOException {
    return HiddenFileFilter.VISIBLE.accept(directory);
  }

  @Override
  protected void handleFile(File file, int depth, Collection<File> results) throws IOException {
    if (filter.accept(file)) {
      results.add(file);
    }
  }

}
