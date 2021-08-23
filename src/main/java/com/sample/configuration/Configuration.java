package com.sample.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

import static org.testng.Assert.assertNotNull;

public class Configuration {

    private static final String CONFIG_FILE_NAME = "config.properties";

    private Properties configProperties;

    public Configuration() {
        loadProperties();
    }

    private void loadProperties() {
        configProperties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assertNotNull(classLoader);
        InputStream inputStream = classLoader.getResourceAsStream(CONFIG_FILE_NAME);
        try {
            configProperties.load(inputStream);
        } catch (final IOException e) {
            throw new IllegalArgumentException("Error occurred when reading from the input stream.");
        }
    }

    public String getBrowser() {
        return getProperty("browser");
    }

    public String getPlatform() {
        return getProperty("platform");
    }

    public String getUrl() {
        String appUrlRelativePath = getProperty("url");
        return Paths.get(appUrlRelativePath).toAbsolutePath().toString();
    }

    public String getProperty(String propertyName) {
        return configProperties.getProperty(propertyName);
    }
}
