/*
 * Copyright (C) 2014 Michael Joyce <michael@negativespace.net>
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

package ca.nines.ise.cmd;

import ca.nines.ise.Main;
import java.io.InputStream;
import java.util.Properties;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
//import org.antlr.v4.runtime.RuntimeMetaData;
/**
 *
 * @author Michael Joyce <michael@negativespace.net>
 */
public class Version extends Command {

  @Override
  public String description() {
    return "Print version information.";
  }

  @Override
  public void execute(CommandLine cmd) throws Exception {
    InputStream stream = Main.class.getResourceAsStream("version.properties");
    Properties prop = new Properties();
    prop.load(stream);
    System.out.println("iTools version " + prop.getProperty("buildVersion"));
    System.out.println("Compiled with javac " + prop.getProperty("javaCompilerVersion"));
    System.out.println("Java runtime: " + System.getProperty("java.version") + " from " + System.getProperty("java.vendor"));
    System.out.println("Host runtime: " + System.getProperty("os.name") + " " + System.getProperty("os.version") + " " + System.getProperty("os.arch"));
  }

  @Override
  public Options getOptions() {
    Options opts = new Options();
    return opts;
  }

}
