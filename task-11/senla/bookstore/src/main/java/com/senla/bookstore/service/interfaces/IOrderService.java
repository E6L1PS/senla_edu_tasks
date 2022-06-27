package com.senla.bookstore.service.interfaces;

import com.senla.bookstore.model.Order;
import com.senla.bookstore.model.OrderStatus;
import com.senla.bookstore.model.Customer;
import com.senla.bookstore.repository.interfaces.IOrderRepository;
import com.senla.bookstore.repository.interfaces.IRequestRepository;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {

    IOrderRepository getOrderRepository();

    IRequestRepository getRequestRepository();

    void addOrder(Customer customer, List<Integer> bookIds);

    void removeOrder(int orderId);

    List<Order> getSortedOrders(String key);

    List<Order> getCompletedOrders(LocalDate startDate, LocalDate endDate, String sortType);

    int getFullPrice(LocalDate startDate, LocalDate endDate);

    int getQuantityCompletedOrders(LocalDate startDate, LocalDate endDate);

    void setStatus(int orderId, OrderStatus orderStatus);

    void exportOrders();

    void exportOrder(int id);

    void importOrders();
}
