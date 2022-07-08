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

    void removeOrder(Integer orderId);

    List<Order> getSortedOrders(String key);

    List<Order> getCompletedOrders(LocalDate startDate, LocalDate endDate, String sortType);

    Integer getFullPrice(LocalDate startDate, LocalDate endDate);

    Integer getQuantityCompletedOrders(LocalDate startDate, LocalDate endDate);

    void setStatus(Integer orderId, OrderStatus orderStatus);

    void exportOrders();

    void exportOrder(Integer id);

    void importOrders();
}
