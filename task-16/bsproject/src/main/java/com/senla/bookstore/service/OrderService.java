package com.senla.bookstore.service;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.BookStatus;
import com.senla.bookstore.model.Customer;
import com.senla.bookstore.model.Order;
import com.senla.bookstore.model.OrderStatus;
import com.senla.bookstore.model.Request;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import com.senla.bookstore.repository.interfaces.ICustomerRepository;
import com.senla.bookstore.repository.interfaces.IOrderRepository;
import com.senla.bookstore.repository.interfaces.IRequestRepository;
import com.senla.bookstore.service.interfaces.IOrderService;
import com.senla.bookstore.util.csv.CsvReader;
import com.senla.bookstore.util.csv.CsvWriter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    private ICustomerRepository customerRepository;

    @Autowired
    private IRequestRepository requestRepository;

    @Transactional(readOnly = true)
    public List<Order> getSortedOrders(String sortType) {
        return orderRepository.findAllByType(sortType);
    }

    @Transactional(readOnly = true)
    public List<Order> getCompletedOrders(LocalDate startDate, LocalDate endDate, String sortType) {
        return orderRepository.findCompletedByType(startDate, endDate, sortType);
    }

    @Transactional
    public Long getQuantityCompletedOrders(LocalDate startDate, LocalDate endDate) {
        return orderRepository.getQuantityCompletedOrders(startDate, endDate);
    }

    @Transactional
    public Integer getFullPrice(LocalDate startDate, LocalDate endDate) {
        return (Integer) orderRepository.getFullPrice(startDate, endDate);
    }

    @Transactional
    public void setStatus(Integer orderId, OrderStatus orderStatus) {
        orderRepository.setStatus(orderId, orderStatus);
    }

    @Transactional
    public void addOrder(List<Book> books) {
        //hardcode customer
        Order order = new Order((Customer) customerRepository.findEntityById(1), books, (Integer) bookRepository.checkPrice(books));
        checkBook(books);
        orderRepository.create(order);
        System.out.println("Создан новый заказ");
    }

    @Transactional
    public void removeOrder(Integer orderId) {
        orderRepository.findEntityById(orderId).setStatus(OrderStatus.CANCELLED);
        System.out.println("Заказ № " + orderId + " отменен");
    }

    @Transactional
    public void checkBook(List<Book> books) {
        for (int i = 0; i < bookRepository.findAll().size(); i++) {
            for (Book book : books) {
                Book bookById = bookRepository.findEntityById(i);
                if (i == book.getId() && bookById.getStatus() == BookStatus.OUT_STOCK) {
                    requestRepository.create(new Request(bookById, "text :)"));
                    System.out.println("Создан запрос на книгу " + i);
                }
            }
        }
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