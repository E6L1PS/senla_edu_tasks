package com.senla.bookstore.controller;

import com.senla.bookstore.model.Customer;
import com.senla.bookstore.model.Order;
import com.senla.bookstore.model.OrderStatus;
import com.senla.bookstore.repository.interfaces.IOrderRepository;
import com.senla.bookstore.repository.interfaces.IRequestRepository;
import com.senla.bookstore.service.interfaces.IOrderService;
import com.senla.configure.annotations.Autowired;
import com.senla.configure.annotations.Singleton;

import java.time.LocalDate;
import java.util.List;

@Singleton
public class OrderController {

    @Autowired
    private IOrderService orderService;

    public OrderController() {

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

    public List<Order> getSortedOrders(String sortType) {
        return orderService.getSortedOrders(sortType);
    }

    public List<Order> getCompletedOrders(LocalDate startDate, LocalDate endDate, String sortType) {
        return orderService.getCompletedOrders(startDate, endDate, sortType);
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
