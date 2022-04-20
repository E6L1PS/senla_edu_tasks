package ru.mirea.senla.bookstore.controller;

import ru.mirea.senla.bookstore.model.BookStatus;
import ru.mirea.senla.bookstore.model.Customer;
import ru.mirea.senla.bookstore.model.Order;
import ru.mirea.senla.bookstore.model.OrderStatus;
import ru.mirea.senla.bookstore.model.csv.CsvOrderWriter;
import ru.mirea.senla.bookstore.service.BookService;
import ru.mirea.senla.bookstore.service.OrderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void addOrder(Customer customer, List<Integer> bookIds) {
        orderService.addOrder(customer, bookIds);
    }

    public void removeOrder(int orderId) {
        orderService.removeOrder(orderId);
    }

    public List<Order> getSortedOrders(String key) {
        return orderService.getSortedOrders(key);
    }

    public List<Order> getCompletedOrders(LocalDate startDate, LocalDate endDate, Comparator comparator) {
        return orderService.getCompletedOrders(startDate, endDate, comparator);
    }

    public int getFullPrice(LocalDate startDate, LocalDate endDate) {
        return orderService.getFullPrice(startDate, endDate);
    }

    public int getQuantityCompletedOrders(LocalDate startDate, LocalDate endDate) {
        return orderService.getQuantityCompletedOrders(startDate, endDate);
    }

    public void setStatus(int orderId, OrderStatus orderStatus) {
        orderService.setStatus(orderId, orderStatus);
    }

    public void exportOrders() {
        orderService.exportOrders();
    }
    public void exportOrder(int id) {
        orderService.exportOrder(id);
    }

    public void importOrders() {
        orderService.importOrders();
    }
}
