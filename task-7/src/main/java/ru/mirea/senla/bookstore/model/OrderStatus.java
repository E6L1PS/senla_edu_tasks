package ru.mirea.senla.bookstore.model;

public enum OrderStatus {

    NEW("новый"), COMPLETED("выполнен"), CANCELLED("отменен");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getNameStatus() {
        return name;
    }
}

