package ru.mirea.senla.task3.task3_4;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private int orderId = 0;
    private List<Order> orders = new ArrayList<>();
    private Warehouse warehouse = new Warehouse();

    public void addOrder(Customer customer, int[] booksId) {
        Order order = new Order(orderId, customer, booksId);
        orders.add(order);
        order.setStatus(OrderStatus.NEW);
        warehouse.checkBook(booksId, order);
        orderId++;
        System.out.println("Создан новый заказ, статус заказа: " + order.getStatus());
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setStatus(OrderStatus.CANCELLED);
        System.out.println("Заказ отменен, статус заказа: " + order.getStatus());
    }

    public Order getOrder(int orderId) {
        return orders.get(orderId);
    }

    public void addToWarehouse(int[] booksId) {
        for (int i = 0; i < booksId.length; i++) {
            warehouse.addBook(booksId[i]);
        }
    }

    public void removeToWarehouse(int[] booksId) {
        for (int i = 0; i < booksId.length; i++) {
            warehouse.removeBook(booksId[i]);
        }
    }

    public void addRequest(int bookId, Order order) {
        if (warehouse.getBook(bookId).getStatus() == "отсутствует") {
            warehouse.getBook(bookId).request(order);
        } else System.out.println("Не удалось оставить запрос на книгу, книга " + bookId + " есть в наличии");
    }
}
