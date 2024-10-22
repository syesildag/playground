package com.serkan;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Config {

    public static final Config INSTANCE = new Config();

    private Properties properties;

    private Properties initProps() throws IOException {
        if (this.properties == null) {
            this.properties = new Properties();
            this.properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties"));
        }
        return this.properties;
    }

    public String getProperty(String key) throws IOException {
        return initProps().getProperty(key);
    }

    public <T> T getProperty(String key, Class<T> type) throws IOException {
        String value = getProperty(key);
        if (value == null) {
            return null;
        }
        try {
            Method valueOfMethod = type.getMethod("valueOf", String.class);
            return type.cast(valueOfMethod.invoke(null, value));
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert property value to " + type.getName(), e);
        }
    }
}