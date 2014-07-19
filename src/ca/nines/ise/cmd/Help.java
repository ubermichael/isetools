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
  public void execute(CommandLine cmd)  throws Exception{
    
    System.out.println("help");
    listCommands();
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
              Class<?> cls = Class.forName(packageName + "." + className);
              if (Modifier.isAbstract(cls.getModifiers())) {
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
}
