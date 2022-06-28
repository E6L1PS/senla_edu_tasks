package com.senla.configure.configurators;

import com.senla.configure.ApplicationContext;
import com.senla.configure.annotations.Autowired;

import java.lang.reflect.Field;

public class AutowiredAnnotationObjectConfigurator implements ObjectConfigurator {

    @Override
    public void configure(Object t, ApplicationContext context) throws Exception {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(t, object);
            }
        }
    }
}
