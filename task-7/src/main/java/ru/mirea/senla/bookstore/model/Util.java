package ru.mirea.senla.bookstore.model;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class Util {

    public String getPropertyValue(String key) {
        String propertyValue = "";
        Properties properties = new Properties();

        try {
            Reader reader = new FileReader("src\\main\\resources\\application.properties");
            properties.load(reader);
            reader.close();
            propertyValue = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyValue;
    }

}
