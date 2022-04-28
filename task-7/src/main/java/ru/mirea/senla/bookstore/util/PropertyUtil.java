package ru.mirea.senla.bookstore.util;

import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

public class PropertyUtil {

    private static final String filePath = "src\\main\\resources\\application.properties";

    public String getPropertyValue(String key) {
        String propertyValue = "";
        Properties properties = new Properties();

        try(Reader reader = new FileReader(filePath)) {
            //ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
            properties.load(reader);
            propertyValue = properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propertyValue;
    }
}