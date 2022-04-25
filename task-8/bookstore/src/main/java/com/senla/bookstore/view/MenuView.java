package com.senla.bookstore.view;

import com.senla.bookstore.controller.BookController;
import com.senla.bookstore.controller.OrderController;
import com.senla.bookstore.controller.WarehouseController;
import com.senla.bookstore.view.menu.MenuItem;
import com.senla.bookstore.view.menu.Navigator;
import com.senla.configure.annotations.Autowired;
import com.senla.configure.annotations.Singleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Singleton
public class MenuView {

    @Autowired
    private BookController bookController;

    @Autowired
    private OrderController orderController;

    @Autowired
    private WarehouseController warehouseController;

    @Autowired
    private Navigator navigator;

    public MenuView() {
        this.navigator = new Navigator();
    }

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;

        while (!exit) {
            navigator.printMenu();
            try {
                String line = reader.readLine();
                int choice = Integer.parseInt(line);

                MenuItem item = navigator.getCurrentMenu().getMenuItems()[choice];

                navigator.navigate(choice);
                item.doAction();
                exit = navigator.getExit();
            } catch (NumberFormatException e) {
                System.out.println("Введите цифру пункта");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Такого пункта нет"); ;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
