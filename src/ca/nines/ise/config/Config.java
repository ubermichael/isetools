/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.nines.ise.config;

import java.util.prefs.Preferences;

/**
 *
 * @author michael
 */
public class Config {

  private static final Config instance = new Config();
  private static final Preferences prefs = Preferences.userRoot().node("ca.nines.ise");

  public static Config getInstance() {
    return instance;
  }

  protected Config() {
  }

  public String getSchema() {
    return Config.prefs.get("default_schema", "default.xml");
  }

  public void setSchema(String schema) {
    Config.prefs.put("default_schema", "default.xml");
  }

  public String getSourceDir() {
    return Config.prefs.get("source_dir", "data/sgml");
  }

  public void setSourceDir(String sourceDir) {
    Config.prefs.put("source_dir", "data/sgml");
  }

}
