package com.senla.bookstore.service.interfaces;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Order;
import com.senla.bookstore.model.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {

    void addOrder(List<Book> books);

    void removeOrder(Integer orderId);

    List<Order> getSortedOrders(String key);

    List<Order> getCompletedOrders(LocalDate startDate, LocalDate endDate, String sortType);

    Integer getFullPrice(LocalDate startDate, LocalDate endDate);

    Long getQuantityCompletedOrders(LocalDate startDate, LocalDate endDate);

    void setStatus(Integer orderId, OrderStatus orderStatus);

    void exportOrders();

    void exportOrder(Integer id);

    void importOrders();
}
