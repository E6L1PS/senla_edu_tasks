package ru.mirea.senla.bookstore;

import ru.mirea.senla.bookstore.controller.MenuController;
import ru.mirea.senla.bookstore.repository.BookRepository;
import ru.mirea.senla.bookstore.repository.OrderRepository;
import ru.mirea.senla.bookstore.repository.RequestRepository;
import ru.mirea.senla.bookstore.repository.WarehouseRepository;
import ru.mirea.senla.bookstore.service.BookService;
import ru.mirea.senla.bookstore.service.OrderService;
import ru.mirea.senla.bookstore.service.WarehouseService;

public class Main {
    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepository();
        OrderRepository orderRepository = new OrderRepository();
        WarehouseRepository warehouseRepository = new WarehouseRepository();
        RequestRepository requestRepository = new RequestRepository();

        BookService bookService = new BookService(bookRepository, requestRepository);
        OrderService orderService = new OrderService(orderRepository, bookRepository, requestRepository);
        WarehouseService warehouseService = new WarehouseService(warehouseRepository, bookRepository, requestRepository);

        MenuController menuController = new MenuController(bookService, orderService, warehouseService);

        menuController.run();
    }
}
