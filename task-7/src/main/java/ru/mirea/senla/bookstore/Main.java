package ru.mirea.senla.bookstore;

import ru.mirea.senla.bookstore.controller.BookController;
import ru.mirea.senla.bookstore.controller.MenuController;
import ru.mirea.senla.bookstore.controller.OrderController;
import ru.mirea.senla.bookstore.controller.WarehouseController;
import ru.mirea.senla.bookstore.model.json.JsonReader;
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

        IBookRepository bookRepository = new JsonReader().readBookRepository("Library.json");
        IOrderRepository orderRepository = new JsonReader().readOrderRepository("Orders.json");
        IBookRepository warehouseRepository = new JsonReader().readBookRepository("Warehouse.json");
        IRequestRepository requestRepository = new JsonReader().readRequestRepository("Requests.json");

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
