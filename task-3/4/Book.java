package ru.mirea.senla.task3.task3_4;

import java.util.ArrayList;

public class Book {
    private String status;
    private int id;

    public Book(int id) {
        this.status = "отсутствует";
        this.id = id;
    }

    private ArrayList<Integer> request = new ArrayList<>();

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void request(Order order) {
        request.add(order.getCustomerId());
        System.out.println("Добавился запрос на книгу '" + id + "'");
    }
    public void removeRequest() {
        request.clear();
        System.out.println("Все запросы на книгу '" + id + "' закрыты");
    }
}
