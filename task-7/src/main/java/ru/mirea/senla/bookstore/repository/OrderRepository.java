package ru.mirea.senla.bookstore.repository;

import ru.mirea.senla.bookstore.model.Order;
import ru.mirea.senla.bookstore.repository.interfaces.IOrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {

    private static OrderRepository instance = new OrderRepository();
    private int countOrdersId = 0;
    private List<Order> orders = new ArrayList<>();

    private OrderRepository() {

    }

    @Override
    public int getCountOrdersId() {
        return countOrdersId;
    }


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
        order.setId(countOrdersId++);
        orders.add(order);
    }

    @Override
    public void updateOrder(Order order) {
        int id = order.getId();
       // orders.remove(id);
        deleteOrderById(id);
        orders.add(id, order);
    }

    @Override
    public void deleteOrder(Order order) {
        orders.remove(order);
    }

    @Override
    public void deleteOrderById(int id) {
        orders.remove(getOrderById(id));
    }

    public static OrderRepository getInstance() {
        return instance;
    }
}
