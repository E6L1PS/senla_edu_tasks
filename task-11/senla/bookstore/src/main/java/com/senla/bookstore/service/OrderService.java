package com.senla.bookstore.service;

import com.senla.bookstore.model.*;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import com.senla.bookstore.repository.interfaces.IOrderRepository;
import com.senla.bookstore.repository.interfaces.IRequestRepository;
import com.senla.bookstore.service.interfaces.IOrderService;
import com.senla.bookstore.model.compares.CompareStrategy;
import com.senla.bookstore.util.csv.CsvReader;
import com.senla.bookstore.util.csv.CsvWriter;
import com.senla.configure.annotations.Autowired;
import com.senla.configure.annotations.Singleton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private CompareStrategy compareStrategy;

    private List<Order> completedOrders = new ArrayList<>();

    public OrderService() {

    }

    public IOrderRepository getOrderRepository() {
        return orderRepository;
    }

    public IRequestRepository getRequestRepository() {
        return requestRepository;
    }

    public void setStatus(int orderId, OrderStatus orderStatus) {
        if (orderStatus.equals(OrderStatus.COMPLETED)) {
            Order orderById = orderRepository.findEntityById(orderId);
            if (orderById.getBookIds().stream().allMatch((x) -> bookRepository.findEntityById(x).getStatus() == BookStatus.IN_STOCK)) {
                completedOrders.add(orderById);
                orderById.setStatus(orderStatus);
                orderById.setIssueDate(LocalDate.now());
            } else {
                System.out.println("Заказ не может быть завершен, проверьте статус книги");
            }
        }
    }

    public void addOrder(Customer customer, List<Integer> bookIds) {
        Order order = new Order(customer, bookIds, (Integer) bookRepository.checkPrice(bookIds));
        checkBook(bookIds);
        orderRepository.create(order);
        System.out.println("Создан новый заказ");
    }

    public void removeOrder(int orderId) {
        orderRepository.findEntityById(orderId).setStatus(OrderStatus.CANCELLED);
        System.out.println("Заказ № " + orderId + " отменен");
    }

    public void checkBook(List<Integer> bookIds) {
        for (int i = 0; i < bookRepository.findAll().size(); i++) {
            for (int bookId : bookIds) {
                Book bookById = bookRepository.findEntityById(i);
                if (i == bookId && bookById.getStatus() == BookStatus.OUT_STOCK) {
                    //requestRepository.addRequest(bookById);
                    System.out.println("Создан запрос на книгу " + i);
                }
            }
        }
    }

    public List<Order> getSortedOrders(String sortType) {
        List<Order> sortedOrders = new ArrayList<>(orderRepository.findAll());
        sortedOrders.sort(compareStrategy.getComparator(sortType));
        return sortedOrders;
    }

    public List<Order> getCompletedOrders(LocalDate startDate, LocalDate endDate, String sortType) {
        List<Order> rangeCompletedOrders = new ArrayList<>();

        for (Order order : completedOrders) {
            if (order.getIssueDate().isAfter(startDate) && order.getIssueDate().isBefore(endDate)) {
                rangeCompletedOrders.add(order);
            }
        }

        rangeCompletedOrders.sort(compareStrategy.getComparator(sortType));
        return rangeCompletedOrders;
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

    public void exportOrders() {
        CsvWriter.writeCsvFile("OrdersTableForExport.csv", orderRepository.findAll());
    }

    public void exportOrder(int id) {
        CsvWriter.writeCsvFile("OrdersTableForExport.csv", orderRepository.findEntityById(id));
    }

    public void importOrders() {

        List<Order> oldOrders = orderRepository.findAll();
        int lastId = oldOrders.size() - 1;
        List<Order> newOrders = CsvReader.readCsvFile("OrdersTableForImport.csv", Order.class);

        for (Order order : newOrders) {
            if (order.getId() > lastId) {
              //TODO  orderRepository.addOrder(order);
            } else {
                //TODO  orderRepository.updateOrder(order);
            }
        }
    }

}
