package com.senla.configure;

import com.senla.configure.annotations.Singleton;
import com.senla.configure.configs.Config;
import org.reflections.Reflections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private final Reflections configuratorsScanner = new Reflections("com.senla.configure.configurators");
    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    private ObjectFactory factory;
    private Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public void setFactory(ObjectFactory factory) {
        this.factory = factory;
    }

    public <T> T getObject(Class<T> type) throws Exception {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }

        Class<? extends T> implClass = type;

        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        T t = factory.createObject(implClass);

        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, t);
        }

        return t;
    }


    public Config getConfig() {
        return config;
    }

    public Reflections getConfiguratorsScanner() {
        return configuratorsScanner;
    }
}
