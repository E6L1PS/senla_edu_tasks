package com.senla.bookstore.repository.interfaces;

import com.senla.bookstore.model.Order;

import java.util.List;

public interface IOrderRepository extends IRepository {

    Order getOrderById(int id);

    int getCountOrdersId();

    List<Order> getOrders();

    void addOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(Order order);

    void deleteOrderById(int id);

}
