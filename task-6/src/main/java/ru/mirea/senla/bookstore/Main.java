package ru.mirea.senla.bookstore;

import ru.mirea.senla.bookstore.controller.BookController;
import ru.mirea.senla.bookstore.controller.MenuController;
import ru.mirea.senla.bookstore.controller.OrderController;
import ru.mirea.senla.bookstore.controller.WarehouseController;
import ru.mirea.senla.bookstore.repository.BookRepository;
import ru.mirea.senla.bookstore.repository.OrderRepository;
import ru.mirea.senla.bookstore.repository.RequestRepository;
import ru.mirea.senla.bookstore.repository.WarehouseRepository;
import ru.mirea.senla.bookstore.repository.interfaces.IBookRepository;
import ru.mirea.senla.bookstore.repository.interfaces.IOrderRepository;
import ru.mirea.senla.bookstore.repository.interfaces.IRequestRepository;
import ru.mirea.senla.bookstore.service.BookService;
import ru.mirea.senla.bookstore.service.OrderService;
import ru.mirea.senla.bookstore.service.WarehouseService;
import ru.mirea.senla.bookstore.service.interfaces.IBookService;
import ru.mirea.senla.bookstore.service.interfaces.IOrderService;
import ru.mirea.senla.bookstore.service.interfaces.IWarehouseService;

public class Main {
    public static void main(String[] args) {

        IBookRepository bookRepository = new BookRepository();
        IOrderRepository orderRepository = new OrderRepository();
        IBookRepository warehouseRepository = new WarehouseRepository();
        IRequestRepository requestRepository = new RequestRepository();

        IBookService bookService = new BookService(bookRepository, requestRepository);
        IOrderService orderService = new OrderService(orderRepository, bookRepository, requestRepository);
        IWarehouseService warehouseService = new WarehouseService(warehouseRepository, bookRepository, requestRepository);

        BookController bookController = new BookController(bookService);
        OrderController orderController = new OrderController(orderService);
        WarehouseController warehouseController = new WarehouseController(warehouseService);

        MenuController menuController = new MenuController(bookController, orderController, warehouseController);

        menuController.run();

    }
}
