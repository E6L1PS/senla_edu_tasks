package ru.mirea.senla.bookstore.controller;

import ru.mirea.senla.bookstore.service.BookService;
import ru.mirea.senla.bookstore.service.OrderService;

public class OrderController {
    private OrderService orderService;
    private BookService bookService;

    public OrderController(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public BookService getBookService() {
        return bookService;
    }
}
