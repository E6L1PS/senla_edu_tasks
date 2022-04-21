package ru.mirea.senla.bookstore.service.interfaces;

import ru.mirea.senla.bookstore.model.Customer;
import ru.mirea.senla.bookstore.model.Order;
import ru.mirea.senla.bookstore.model.OrderStatus;
import ru.mirea.senla.bookstore.repository.interfaces.IOrderRepository;
import ru.mirea.senla.bookstore.repository.interfaces.IRequestRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public interface IOrderService {

    IOrderRepository getOrderRepository();

    IRequestRepository getRequestRepository();

    void addOrder(Customer customer, List<Integer> bookIds);

    void removeOrder(int orderId);

    List<Order> getSortedOrders(String key);

    List<Order> getCompletedOrders(LocalDate startDate, LocalDate endDate, Comparator comparator);

    int getFullPrice(LocalDate startDate, LocalDate endDate);

    int getQuantityCompletedOrders(LocalDate startDate, LocalDate endDate);

    void setStatus(int orderId, OrderStatus orderStatus);

    void exportOrders();

    void exportOrder(int id);

    void importOrders();
}
