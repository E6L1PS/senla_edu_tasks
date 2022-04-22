package ru.mirea.senla.bookstore.repository;

import ru.mirea.senla.bookstore.model.Order;
import ru.mirea.senla.bookstore.repository.interfaces.IOrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {

    private List<Order> orders = new ArrayList<>();

    @Override
    public Order getOrderById(int id) {
        return orders.stream().filter(order -> order.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public Order updateOrder(Order order) {
        int id = order.getId();
        orders.remove(id);
        orders.add(id, order);
        return null;
    }

    @Override
    public void deleteOrder(Order order) {
        orders.remove(order);
    }

    @Override
    public void deleteOrderById(int id) {
        orders.remove(id);
    }
}
