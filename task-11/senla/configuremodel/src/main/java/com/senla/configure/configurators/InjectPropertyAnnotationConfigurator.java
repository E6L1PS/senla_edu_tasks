package com.senla.configure.configurators;

import com.senla.configure.ApplicationContext;
import com.senla.configure.annotations.ConfigProperty;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.Properties;

public class InjectPropertyAnnotationConfigurator implements ObjectConfigurator {

    private Properties properties = new Properties();

    public InjectPropertyAnnotationConfigurator() {
        String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
        try (Reader reader = new FileReader(path)) {
            properties.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void configure(Object t, ApplicationContext context) {

        Class<?> implClass = t.getClass();

        for (Field field : implClass.getDeclaredFields()) {
            ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
            if (annotation != null) {
                var value = annotation.value().isEmpty() ? properties.get(field.getName()) : properties.get(annotation.value());
                field.setAccessible(true);
                try {
                    field.set(t, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
