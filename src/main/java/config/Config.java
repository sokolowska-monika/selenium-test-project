package config;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private Properties properties;

    private Properties getProperties() {
        Properties prop = new Properties();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            prop.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Cannot load properties file: " + e);
        }
        return prop;
    }

    public Config() {
        properties = getProperties();
    }

    public String getApplicationUrl() {
        return properties.getProperty("application.url");
    }

    public String getApplicationUser() {
        return properties.getProperty("application.user");
    }

    public String getApplicationPassword() {
        return properties.getProperty("application.password");
    }

    public String getApplicationProcesses() {
        return properties.getProperty("application.processes");
    }

    public String getApplicationDashboards() {
        return properties.getProperty("application.dashboards");
    }

    public String getApplicationCharacteristics() {
        return properties.getProperty("application.characteristics");
    }


    public String getApplicationProcessName() {
        return properties.getProperty("application.processName");
    }

    public String getApplicationProcessDescription() {
        return properties.getProperty("application.processDescription");
    }

    public String getApplicationProcessNotes() {
        return properties.getProperty("application.processNotes");
    }
}

