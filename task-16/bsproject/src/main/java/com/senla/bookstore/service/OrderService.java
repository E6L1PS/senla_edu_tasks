package com.senla.bookstore.service;

import com.senla.bookstore.model.*;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import com.senla.bookstore.repository.interfaces.IOrderRepository;
import com.senla.bookstore.repository.interfaces.IRequestRepository;
import com.senla.bookstore.service.interfaces.IOrderService;
import com.senla.bookstore.model.compares.CompareStrategy;
import com.senla.bookstore.util.csv.CsvReader;
import com.senla.bookstore.util.csv.CsvWriter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@NoArgsConstructor
@Service
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

    public IOrderRepository getOrderRepository() {
        return orderRepository;
    }

    public IRequestRepository getRequestRepository() {
        return requestRepository;
    }

    @Transactional
    public void setStatus(Integer orderId, OrderStatus orderStatus) {
        orderRepository.setStatus(orderId, orderStatus);
    }

    @Transactional
    public void addOrder(Customer customer, List<Integer> bookIds) {
        Order order = new Order(customer, new ArrayList<Book>(), (Integer) bookRepository.checkPrice(bookIds));
        checkBook(bookIds);
        orderRepository.create(order);
        System.out.println("Создан новый заказ");
    }

    @Transactional
    public void removeOrder(Integer orderId) {
        orderRepository.findEntityById(orderId).setStatus(OrderStatus.CANCELLED);
        System.out.println("Заказ № " + orderId + " отменен");
    }

    @Transactional
    public void checkBook(List<Integer> bookIds) {
        for (int i = 0; i < bookRepository.findAll().size(); i++) {
            for (int bookId : bookIds) {
                Book bookById = bookRepository.findEntityById(i);
                if (i == bookId && bookById.getStatus() == BookStatus.OUT_STOCK) {
                    requestRepository.create(new Request(bookById, "text :)"));
                    System.out.println("Создан запрос на книгу " + i);
                }
            }
        }
    }

    @Transactional(readOnly = true)
    public List<Order> getSortedOrders(String sortType) {
        return orderRepository.findAllByType(sortType);
    }

    @Transactional(readOnly = true)
    public List<Order> getCompletedOrders(LocalDate startDate, LocalDate endDate, String sortType) {
        return orderRepository.findCompletedByType(startDate, endDate, sortType);

       /* List<Order> rangeCompletedOrders = new ArrayList<>();

        for (Order order : completedOrders) {
            if (order.getIssueDate().isAfter(startDate) && order.getIssueDate().isBefore(endDate)) {
                rangeCompletedOrders.add(order);
            }
        }

        rangeCompletedOrders.sort(compareStrategy.getComparator(sortType));
        return rangeCompletedOrders;*/
    }

    @Transactional
    public Integer getFullPrice(LocalDate startDate, LocalDate endDate) {
        Integer sum = 0;

        for (Order order : completedOrders) {
            if (order.getIssueDate().isAfter(startDate) && order.getIssueDate().isBefore(endDate)) {
                sum += order.getPrice();
            }
        }

        return sum;
    }

    @Transactional
    public Integer getQuantityCompletedOrders(LocalDate startDate, LocalDate endDate) {
        Integer sum = 0;

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

    public void exportOrder(Integer id) {
        CsvWriter.writeCsvFile("OrdersTableForExport.csv", orderRepository.findEntityById(id));
    }

    public void importOrders() {

        List<Order> oldOrders = orderRepository.findAll();
        Integer lastId = oldOrders.size() - 1;
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