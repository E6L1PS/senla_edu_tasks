package ru.mirea.senla.bookstore.service;

import ru.mirea.senla.bookstore.model.*;
import ru.mirea.senla.bookstore.repository.BookRepository;
import ru.mirea.senla.bookstore.repository.OrderRepository;
import ru.mirea.senla.bookstore.repository.RequestRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderService {

    private OrderRepository orderRepository;
    private BookRepository bookRepository;
    private RequestRepository requestRepository;

    private List<Order> completedOrders = new ArrayList<>();

    public OrderService(OrderRepository orderRepository, BookRepository bookRepository, RequestRepository requestRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.requestRepository = requestRepository;
    }

    public void setStatus(int orderId, OrderStatus orderStatus) {
        if (orderStatus.equals(OrderStatus.COMPLETED)) {
            Order orderById = orderRepository.getOrderById(orderId);
            if (orderById.getBookIds().stream().allMatch((x) -> bookRepository.getBookById(x).getStatus() == BookStatus.IN_STOCK)) {
                completedOrders.add(orderById);
                orderById.setStatus(orderStatus);
                orderById.setIssueDate(LocalDate.now());
            } else {
                System.out.println("Заказ не может быть завершен, проверьте статус книги");
            }
        }
    }

    public void addOrder(Customer customer, List<Integer> bookIds) {
        Order order = new Order(customer, bookIds, bookRepository.checkPrice(bookIds));
        checkBook(bookIds);
        orderRepository.addOrder(order);
        System.out.println("Создан новый заказ");
    }

    public void removeOrder(int orderId) {
        orderRepository.getOrderById(orderId).setStatus(OrderStatus.CANCELLED);
        System.out.println("Заказ № " + orderId + " отменен");
    }

    public void checkBook(List<Integer> bookIds) {
        for (int i = 0; i < bookRepository.getBooks().size(); i++) {
            for (int bookId : bookIds) {
                Book bookById = bookRepository.getBookById(i);
                if (i == bookId && bookById.getStatus() == BookStatus.OUT_STOCK) {
                    requestRepository.addRequest(bookById);
                    System.out.println("Создан запрос на книгу " + i);
                }
            }
        }
    }

    public List<Order> getSortedOrders(Comparator comparator) {
        List<Order> sortedOrders = new ArrayList<>(orderRepository.getOrders());
        sortedOrders.sort(comparator);
        return sortedOrders;
    }

    public List<Order> getCompletedOrders(LocalDate startDate, LocalDate endDate, Comparator comparator) {
        List<Order> diapasonCompletedOrders = new ArrayList<>();

        for (Order order : completedOrders) {
            if (order.getIssueDate().isAfter(startDate) && order.getIssueDate().isBefore(endDate)) {
                diapasonCompletedOrders.add(order);
            }
        }

        diapasonCompletedOrders.sort(comparator);
        return diapasonCompletedOrders;
    }

    public int getFullPrice(LocalDate startDate, LocalDate endDate) {
        int sum = 0;

        for (Order order : completedOrders) {
            if (order.getIssueDate().isAfter(startDate) && order.getIssueDate().isBefore(endDate)) {
                sum += order.getPrice();
            }
        }

        return sum;
    }

    public int getQuantityCompletedOrders(LocalDate startDate, LocalDate endDate) {
        int sum = 0;

        for (Order order : completedOrders) {
            if (order.getIssueDate().isAfter(startDate) && order.getIssueDate().isBefore(endDate)) {
                sum++;
            }
        }

        return sum;
    }
}
