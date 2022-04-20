package ru.mirea.senla.bookstore.controller;

import ru.mirea.senla.bookstore.model.menu.MenuItem;
import ru.mirea.senla.bookstore.model.menu.Navigator;
import ru.mirea.senla.bookstore.service.BookService;
import ru.mirea.senla.bookstore.service.OrderService;
import ru.mirea.senla.bookstore.service.WarehouseService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {

    private Navigator navigator;

    public MenuController(BookController bookController, OrderController orderController, WarehouseController warehouseController) {
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
    }
}
