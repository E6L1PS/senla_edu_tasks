package ru.mirea.senla.bookstore.view;

import ru.mirea.senla.bookstore.controller.BookController;
import ru.mirea.senla.bookstore.controller.OrderController;
import ru.mirea.senla.bookstore.controller.WarehouseController;
import ru.mirea.senla.bookstore.view.menu.MenuItem;
import ru.mirea.senla.bookstore.view.menu.Navigator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuView {

    private Navigator navigator;

    public MenuView(BookController bookController, OrderController orderController, WarehouseController warehouseController) {
        this.navigator = new Navigator(bookController, orderController, warehouseController);
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
