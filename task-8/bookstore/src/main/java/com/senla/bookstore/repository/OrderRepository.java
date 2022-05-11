package com.senla.bookstore.repository;

import com.senla.bookstore.model.Order;
import com.senla.bookstore.repository.interfaces.IOrderRepository;
import com.senla.bookstore.util.json.JsonReader;
import com.senla.configure.annotations.Singleton;

import java.util.List;

@Singleton
public class OrderRepository implements IOrderRepository {

    private static int countOrdersId;
    private List<Order> orders;

    public OrderRepository() {
        orders = JsonReader.readRepository("Orders.json", Order.class);
        countOrdersId = orders.size();
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
}
