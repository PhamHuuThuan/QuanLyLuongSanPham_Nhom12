package Util;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

public class ConfigManager {
    private Properties configProps;
    private String configPath;

    public ConfigManager(String configPath) {
        this.configPath = configPath;
        configProps = new Properties();

        try {
            InputStream configStream = getClass().getResourceAsStream(configPath);
            configProps.load(configStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getSoundSetting() {
        return Integer.parseInt(configProps.getProperty("sound_setting"));
    }

    public int getLanguage() {
        return Integer.parseInt(configProps.getProperty("language"));
    }

    public boolean getDarkMode() {
        return configProps.getProperty("dark_mode").equals("1");
    }

    public void setSoundSetting(int value) {
        configProps.setProperty("sound_setting", Integer.toString(value));
        saveChanges();
    }

    public void setLanguage(int language) {
        configProps.setProperty("language", Integer.toString(language));
        saveChanges();
    }

    public void setDarkMode(boolean enabled) {
        configProps.setProperty("dark_mode", enabled ? "1" : "0");
        saveChanges();
    }

    public boolean getRememberAccount() {
        return configProps.getProperty("remember_account").startsWith("1");
    }

    public String getUsername() {
        if (getRememberAccount()) {
            return configProps.getProperty("remember_account").split(" ")[1];
        } else {
            return null;
        }
    }

    public String getPassword() {
        if (getRememberAccount()) {
            return configProps.getProperty("remember_account").split(" ")[2];
        } else {
            return null;
        }
    }

    public void setRememberAccount(boolean remember, String username, String password) {
        if (remember) {
            configProps.setProperty("remember_account", "1 " + username + " " + password);
        } else {
            configProps.setProperty("remember_account", "0");
        }
        saveChanges();
    }

    private void saveChanges() {
        try {
            FileWriter writer = new FileWriter(new File(getClass().getResource(configPath).toURI()));
            configProps.store(writer, "Host settings");
            writer.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
}

