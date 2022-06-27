package com.senla.bookstore;

import com.senla.bookstore.view.MenuView;
import com.senla.configure.Application;
import com.senla.configure.ApplicationContext;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = Application.run("com.senla.bookstore", new HashMap<>());
        MenuView menuView = context.getObject(MenuView.class);
        menuView.run();
    }
}
