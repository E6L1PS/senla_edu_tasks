package ru.mirea.senla.bookstore;

import ru.mirea.senla.bookstore.controller.BookController;
import ru.mirea.senla.bookstore.repository.BookRepository;
import ru.mirea.senla.bookstore.repository.OrderRepository;
import ru.mirea.senla.bookstore.repository.RequestRepository;
import ru.mirea.senla.bookstore.repository.WarehouseRepository;
import ru.mirea.senla.bookstore.view.MenuView;
import ru.mirea.senla.bookstore.controller.OrderController;
import ru.mirea.senla.bookstore.controller.WarehouseController;
import ru.mirea.senla.bookstore.util.json.JsonReader;
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
        JsonReader reader = new JsonReader();

        IBookRepository bookRepository = reader.readRepository("Library.json", BookRepository.getInstance());
        IOrderRepository orderRepository = reader.readRepository("Orders.json", OrderRepository.getInstance());
        IBookRepository warehouseRepository = reader.readRepository("Warehouse.json", WarehouseRepository.getInstance());
        IRequestRepository requestRepository = reader.readRepository("Requests.json", RequestRepository.getInstance());

        IBookService bookService = new BookService(bookRepository, requestRepository);
        IOrderService orderService = new OrderService(orderRepository, bookRepository, requestRepository);
        IWarehouseService warehouseService = new WarehouseService(warehouseRepository, bookRepository, requestRepository);

        BookController bookController = new BookController(bookService);
        OrderController orderController = new OrderController(orderService);
        WarehouseController warehouseController = new WarehouseController(warehouseService);

        MenuView menuView = new MenuView(bookController, orderController, warehouseController);

        menuView.run();

    }
}
