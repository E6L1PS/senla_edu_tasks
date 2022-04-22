package ru.mirea.senla.bookstore.model.menu;

import java.util.Arrays;

public class Menu {
    private String name;
    private MenuItem[] menuItems;

    public Menu(String name, MenuItem[] menuItems) {
        this.name = name;
        this.menuItems = menuItems;
    }

    public String getName() {
        return name;
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenuItems(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String toString() {
        return "----------" + name + "----------\n" +
                Arrays.toString(menuItems)
                        .replaceAll(", ", "")
                        .replace("[", "")
                        .replace("]", "");
    }

}
