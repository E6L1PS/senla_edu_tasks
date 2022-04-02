package ru.mirea.senla.bookstore.repository;

import ru.mirea.senla.bookstore.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrderById(int id) {
        return orders.stream().filter(order -> order.getId() == id).findAny().orElse(null);
    }
    public void addOrder(Order order) {
        orders.add(order);
    }
}
