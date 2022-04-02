package ru.mirea.senla.bookstore.service;

import ru.mirea.senla.bookstore.model.*;
import ru.mirea.senla.bookstore.repository.BookRepository;
import ru.mirea.senla.bookstore.repository.OrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderService {

    private OrderRepository orderRepository;
    private BookRepository bookRepository;
    private List<Order> sortedOrders;
    private List<Order> completedOrders = new ArrayList<>();

    public OrderService(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.sortedOrders = new ArrayList<>(orderRepository.getOrders());
    }

    public void setStatus(int orderId, OrderStatus orderStatus) {
        if (orderStatus.equals(OrderStatus.COMPLETED)) {
            if (Arrays.stream(orderRepository.getOrderById(orderId).getBookIds()).allMatch((x) -> bookRepository.getBookById(x).getStatus() == BookStatus.IN_STOCK)) {
                completedOrders.add(orderRepository.getOrderById(orderId));
                orderRepository.getOrderById(orderId).setStatus(orderStatus);
                orderRepository.getOrderById(orderId).setIssueDate(LocalDate.now());
            } else {
                System.out.println("Заказ не может быть завершен, проверьте статус книги");
            }
        }
    }

    public void addOrder(Customer customer, int[] bookIds) {
        Order order = new Order(customer, bookIds, checkPrice(bookIds));
        orderRepository.addOrder(order);
        sortedOrders.add(order);
        checkBook(bookIds);
        System.out.println("Создан новый заказ");
    }

    public void removeOrder(int orderId) {
        orderRepository.getOrderById(orderId).setStatus(OrderStatus.CANCELLED);
        System.out.println("Заказ № " + orderId + " отменен");
    }

    public int checkPrice(int[] bookIds) {
        int sum = 0;

        for (int bookId : bookIds) {
            sum += bookRepository.getBookById(bookId).getPrice();
        }

        return sum;
    }

    public void checkBook(int[] bookIds) {
        for (int i = 0; i < bookRepository.getBooks().size(); i++) {
            for (int bookId : bookIds) {
                if (i == bookId && bookRepository.getBookById(i).getStatus() == BookStatus.OUT_STOCK) {
                    bookRepository.getBookById(i).addRequest();
                }
            }
        }
    }

    public String showOrders() {
        return sortedOrders.toString().replaceAll(", ", "")
                .replace("[", "")
                .replace("]", "");
    }

    //Сортировка заказов
    public void sortOrdersByPrice() {
        sortedOrders.sort((order1, order2) -> order1.getPrice() - order2.getPrice());
    }

    public void sortOrdersByDate() {
        sortedOrders.sort((order1, order2) -> order1.getIssueDate().compareTo(order2.getIssueDate()));
    }

    public void sortOrdersByStatus() {
        sortedOrders.sort((order1, order2) -> order1.getStatus().compareTo(order2.getStatus()));
    }

    public String showCompletedOrders(LocalDate startDate, LocalDate endDate) {
        List<Order> diapasonCompletedOrders = new ArrayList<Order>();

        for (Order order : completedOrders) {
            if (order.getIssueDate().isAfter(startDate) && order.getIssueDate().isBefore(endDate)) {
                diapasonCompletedOrders.add(order);
            }
        }

        return diapasonCompletedOrders.toString();
    }

    //Сортировка выполненных заказов
    public void sortCompletedOrdersByPrice() {
        completedOrders.sort((order1, order2) -> order1.getPrice() - order2.getPrice());
    }

    public void sortCompletedOrdersByDate() {
        completedOrders.sort((order1, order2) -> order1.getIssueDate().compareTo(order2.getIssueDate()));
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
