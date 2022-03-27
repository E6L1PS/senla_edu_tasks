package ru.mirea.senla.task3.task3_4;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private BookStatus status;
    private int id;
    private List<Integer> request = new ArrayList<>();

    public Book(int id) {
        this.status = BookStatus.OUT_STOCK;
        this.id = id;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status.getNameStatus();
    }

    public int getId() {
        return id;
    }

    public void request(Order order) {
        request.add(order.getCustomer().getCustomerId());
        System.out.println("Добавился запрос на книгу '" + id + "'");
    }

    public void removeRequest() {
        request.clear();
        System.out.println("Все запросы на книгу '" + id + "' закрыты");
    }
}
