package com.senla.bookstore.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {

    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try (FileInputStream fis = new FileInputStream("bookstore\\src\\main\\resources\\bookstoredb.properties")) {
            PROPERTIES.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
