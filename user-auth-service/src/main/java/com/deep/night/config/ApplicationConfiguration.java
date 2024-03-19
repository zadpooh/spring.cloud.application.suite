package com.deep.night.config;

import java.util.Properties;

/**
 * @author hajimaro
 */
public class ApplicationConfiguration {

    public static class application {
        public static Properties property = null;
        public static Properties message = null;
    }

    public static String getProperty(String key) {
        return application.property.getProperty(key);
    }

    public static String getMessage(String key) {
        return application.message.getProperty(key);
    }

}
