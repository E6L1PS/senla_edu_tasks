package com.senla.configure;

import com.senla.configure.configurators.ObjectConfigurator;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class ObjectFactory {

    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();

    public ObjectFactory(ApplicationContext context) throws Exception {
        this.context = context;

        for (Class<? extends ObjectConfigurator> aClass : context.getConfiguratorsScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getConstructor().newInstance());
        }
    }

    public <T> T createObject(Class<T> implClass) throws Exception {

        T t = implClass.getDeclaredConstructor().newInstance();

        configureAll(t);

        invokeInit(implClass, t);

        return t;
    }

    private <T> void invokeInit(Class<T> implClass, T t) throws InvocationTargetException, IllegalAccessException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }

    private <T> void configureAll(T t) {
        configurators.forEach(objectConfigurator -> {
            try {
                objectConfigurator.configure(t, context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}
