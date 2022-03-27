package ru.mirea.senla.task3.task3_4;

public enum BookStatus {
    IN_STOCK("в наличии"), OUT_STOCK("отсутсвует");
    private String name;

    BookStatus(String name) {
        this.name = name;
    }

    public String getNameStatus() {
        return name;
    }
}
