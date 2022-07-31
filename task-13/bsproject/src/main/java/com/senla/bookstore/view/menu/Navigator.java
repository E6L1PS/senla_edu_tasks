package com.senla.bookstore.view.menu;

import com.senla.bookstore.controller.BookController;
import com.senla.bookstore.controller.OrderController;
import com.senla.bookstore.controller.WarehouseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Stack;

@Component
public class Navigator {

    @Autowired
    private BookController bookController;

    @Autowired
    private OrderController orderController;

    @Autowired
    private WarehouseController warehouseController;

    @Autowired
    private Menu rootMenu;
    private Menu currentMenu;
    private Stack<Menu> menuStack = new Stack<>();
    private boolean exitValue = false;

    public Navigator() {
        //  this.currentMenu = rootMenu;
        // menuStack.push(rootMenu);
    }

    @PostConstruct
    public void init() {
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
