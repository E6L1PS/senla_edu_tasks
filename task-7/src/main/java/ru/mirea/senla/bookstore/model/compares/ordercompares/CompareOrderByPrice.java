package ru.mirea.senla.bookstore.model.compares.ordercompares;

import ru.mirea.senla.bookstore.model.Order;

import java.util.Comparator;

public class CompareOrderByPrice implements Comparator<Order> {


    @Override
    public int compare(Order order1, Order order2) {
        return order1.getPrice() -  order2.getPrice();
    }
}