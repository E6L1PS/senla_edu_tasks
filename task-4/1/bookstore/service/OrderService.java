package ru.mirea.senla.bookstore.service;

import ru.mirea.senla.bookstore.model.*;
import ru.mirea.senla.bookstore.repository.BookRepository;
import ru.mirea.senla.bookstore.repository.OrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    //@Autowired
    private OrderRepository orderRepository;
    private BookRepository bookRepository;
    private List<Order> sortedOrders;
    private List<Order> completedOrders = new ArrayList<>();

    public OrderService(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.sortedOrders = orderRepository.getOrders();
    }

    public void setStatus(int id, OrderStatus orderStatus) {
        if (orderStatus.equals(OrderStatus.COMPLETED)) {
            completedOrders.add(orderRepository.getOrders().get(id));
            orderRepository.getOrders().get(id).setStatus(orderStatus);
            orderRepository.getOrders().get(id).setIssueDate(LocalDate.now());
        }
    }

    public void  addOrder(Customer customer, int[] booksId) {
        orderRepository.getOrders().add(new Order(customer, booksId, checkPrice(booksId)));
        checkBook(customer, booksId);
        System.out.println("Создан новый заказ");
    }

    public void removeOrder(int orderId) {
        orderRepository.getOrders().get(orderId).setStatus(OrderStatus.CANCELLED);
        System.out.println("Заказ № " + orderId + " отменен");
    }

    public int checkPrice(int[] booksId) {
        int sum = 0;
        for (int i : booksId) {
            sum += bookRepository.getBooks().get(i).getPrice();
        }
        return sum;
    }

    public void checkBook(Customer customer, int[] booksId) {
        for(int i = 0; i < bookRepository.getBooks().size(); i++) {
            for(int j = 0; j < booksId.length; j++) {
                if(bookRepository.getBooks().get(i).getId() == booksId[j] && bookRepository.getBooks().get(i).getStatus() == BookStatus.OUT_STOCK) {
                    bookRepository.getBooks().get(i).getRequest().add(customer.getCustomerId());
                }
            }
        }
    }

    public String showOrders() {
        return sortedOrders.toString();
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
