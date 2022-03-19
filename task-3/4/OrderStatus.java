package ru.mirea.senla.task3.task3_4;

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

