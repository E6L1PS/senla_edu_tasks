package ru.mirea.senla.bookstore.controller;

import ru.mirea.senla.bookstore.model.Customer;
import ru.mirea.senla.bookstore.model.Order;
import ru.mirea.senla.bookstore.model.OrderStatus;
import ru.mirea.senla.bookstore.repository.interfaces.IOrderRepository;
import ru.mirea.senla.bookstore.repository.interfaces.IRequestRepository;
import ru.mirea.senla.bookstore.service.interfaces.IOrderService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class OrderController {

    private IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    public IOrderRepository getOrderRepository() {
        return orderService.getOrderRepository();
    }

    public IRequestRepository getRequestRepository() {
        return orderService.getRequestRepository();
    }

    public IOrderService getOrderService() {
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
