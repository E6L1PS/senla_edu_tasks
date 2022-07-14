package com.senla.bookstore;

import com.senla.bookstore.view.MenuView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        MenuView menuView = applicationContext.getBean(MenuView.class);
        menuView.run();
    }
}