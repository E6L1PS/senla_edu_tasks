package com.senla.bookstore.model.compares.ordercompares;

import com.senla.bookstore.model.Order;

import java.util.Comparator;

public class CompareOrderByStatus implements Comparator<Order> {

    @Override
    public int compare(Order order1, Order order2) {
        return order1.getStatus().compareTo(order2.getStatus());
    }
}