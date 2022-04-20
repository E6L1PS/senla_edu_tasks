package ru.mirea.senla.bookstore.repository.interfaces;

import ru.mirea.senla.bookstore.model.Order;

import java.util.List;

public interface IOrderRepository {

    Order getOrderById(int id);

    List<Order> getOrders();

    void addOrder(Order order);

    Order updateOrder(Order order);

    void deleteOrder(Order order);

    void deleteOrderById(int id);

}
