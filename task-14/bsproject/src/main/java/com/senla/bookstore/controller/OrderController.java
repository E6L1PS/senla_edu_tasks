package com.senla.bookstore.controller;

import com.senla.bookstore.model.Customer;
import com.senla.bookstore.model.Order;
import com.senla.bookstore.model.OrderStatus;
import com.senla.bookstore.repository.interfaces.IOrderRepository;
import com.senla.bookstore.repository.interfaces.IRequestRepository;
import com.senla.bookstore.service.interfaces.IOrderService;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@NoArgsConstructor
@Controller
public class OrderController {
    @Autowired
    private IOrderService orderService;

    public IOrderRepository getOrderRepository() {
        log.info("Method call 'getOrderRepository'");
        return orderService.getOrderRepository();
    }

    public IRequestRepository getRequestRepository() {
        log.info("Method call 'getRequestRepository'");
        return orderService.getRequestRepository();
    }

    public IOrderService getOrderService() {
        return orderService;
    }

    public void addOrder(Customer customer, List<Integer> bookIds) {
        log.info("Method call 'addOrder'");
        orderService.addOrder(customer, bookIds);
    }

    public void removeOrder(int orderId) {
        log.info("Method call 'removeOrder'");
        orderService.removeOrder(orderId);
    }

    public List<Order> getSortedOrders(String sortType) {
        log.info("Method call 'getSortedOrders'");
        return orderService.getSortedOrders(sortType);
    }

    public List<Order> getCompletedOrders(LocalDate startDate, LocalDate endDate, String sortType) {
        log.info("Method call 'getCompletedOrders'");
        return orderService.getCompletedOrders(startDate, endDate, sortType);
    }

    public int getFullPrice(LocalDate startDate, LocalDate endDate) {
        log.info("Method call 'getFullPrice'");
        return orderService.getFullPrice(startDate, endDate);
    }

    public int getQuantityCompletedOrders(LocalDate startDate, LocalDate endDate) {
        log.info("Method call 'getQuantityCompletedOrders'");
        return orderService.getQuantityCompletedOrders(startDate, endDate);
    }

    public void setStatus(int orderId, OrderStatus orderStatus) {
        log.info("Method call 'setStatus'");
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
