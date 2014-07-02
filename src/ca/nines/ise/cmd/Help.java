/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.cmd;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 *
 * @author michael
 */
public class Help extends Command {

  @Override
  public String description() {
    return "Display useful help messages.";
  }

  @Override
  public Options getOptions() {
    Options opts = new Options();
    return opts;
  }

  public void listCommands() {
    System.out.println("listing commands");
    String packageName = this.getClass().getPackage().getName();
    String packagePath = '/' + packageName.replace('.', '/');

    URL url = this.getClass().getResource(packagePath);
    File dir = new File(url.getFile());
    if (dir.exists()) {
      String[] files = dir.list();
      for (String file : files) {
        if (file.endsWith(".class")) {
          String className = file.substring(0, file.length() - 6);
          try {
            Class cls = Class.forName(packageName + "." + className);
            if(Modifier.isAbstract(cls.getModifiers())) {
              continue;
            }
            Object o = cls.newInstance();
            if (o instanceof Command) {
              System.out.println("command : " + className);
            }
          } catch (ClassNotFoundException ex) {
            Logger.getLogger(Help.class.getName()).log(Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
            Logger.getLogger(Help.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
            Logger.getLogger(Help.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
    }
  }

  @Override
  public void execute(CommandLine cmd) {

    System.out.println("help");
    listCommands();
  }
}