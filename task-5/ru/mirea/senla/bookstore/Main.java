package ru.mirea.senla.bookstore;

import ru.mirea.senla.bookstore.controller.BookController;
import ru.mirea.senla.bookstore.controller.MenuController;
import ru.mirea.senla.bookstore.controller.OrderController;
import ru.mirea.senla.bookstore.controller.WarehouseController;
import ru.mirea.senla.bookstore.model.Customer;
import ru.mirea.senla.bookstore.model.OrderStatus;
import ru.mirea.senla.bookstore.repository.BookRepository;
import ru.mirea.senla.bookstore.repository.OrderRepository;
import ru.mirea.senla.bookstore.repository.WarehouseRepository;
import ru.mirea.senla.bookstore.service.BookService;
import ru.mirea.senla.bookstore.service.OrderService;
import ru.mirea.senla.bookstore.service.WarehouseService;
import ru.mirea.senla.task3.task3_4.Warehouse;

import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        OrderRepository orderRepository = new OrderRepository();
        WarehouseRepository warehouseRepository = new WarehouseRepository();

        BookService bookService = new BookService(bookRepository);
        OrderService orderService = new OrderService(orderRepository, bookRepository);
        WarehouseService warehouseService = new WarehouseService(warehouseRepository, bookRepository);

        MenuController menuController = new MenuController(bookService, orderService, warehouseService);

        menuController.run();
    }
}
