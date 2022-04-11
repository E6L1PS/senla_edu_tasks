package ru.mirea.senla.bookstore.model.menu;

import ru.mirea.senla.bookstore.service.BookService;
import ru.mirea.senla.bookstore.service.OrderService;
import ru.mirea.senla.bookstore.service.WarehouseService;

import java.util.Stack;

public class Navigator {
    private Menu rootMenu;
    private Menu currentMenu;
    Stack <Menu> menuStack = new Stack<>();
    private boolean exitValue = false;

    public Navigator(BookService bookService, OrderService orderService, WarehouseService warehouseService) {
        this.rootMenu = new MainMenu(bookService, orderService, warehouseService);
        this.currentMenu = rootMenu;
        menuStack.push(rootMenu);
    }

    public void printMenu() {
        System.out.println(currentMenu);
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void navigate(int i) {
        if (currentMenu.getMenuItems()[i].getNextMenu() != null) {
            menuStack.push(currentMenu);
            currentMenu = currentMenu.getMenuItems()[i].getNextMenu();
        }
        if (i == 0)
            if (currentMenu == rootMenu) {
                exitValue = true;//exit
            } else {
                currentMenu = menuStack.pop();//back
            }
    }

    public boolean getExit() {
        return exitValue;
    }
}
