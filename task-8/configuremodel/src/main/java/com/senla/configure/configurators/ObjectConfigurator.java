package com.senla.configure.configurators;

import com.senla.configure.ApplicationContext;

public interface ObjectConfigurator {
    void configure(Object t, ApplicationContext context) throws Exception;
}
