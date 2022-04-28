package ru.mirea.senla.bookstore.model;

public enum BookStatus {

    IN_STOCK("в наличии"),
    OUT_STOCK("отсутсвует");

    private String name;

    BookStatus(String name) {
        this.name = name;
    }

    public String getNameStatus() {
        return name;
    }
}
