package ru.mirea.senla.bookstore;

import ru.mirea.senla.bookstore.controller.BookController;
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

import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        OrderRepository orderRepository = new OrderRepository();
        WarehouseRepository warehouseRepository = new WarehouseRepository();

        WarehouseController warehouseController = new WarehouseController(new WarehouseService(warehouseRepository, bookRepository));
        BookController bookController = new BookController(new BookService(bookRepository));
        OrderController orderController = new OrderController(new OrderService(orderRepository, bookRepository), new BookService(bookRepository));

        //Добавление книг на склад
        warehouseController.getWarehouseService().addBook(1);
        warehouseController.getWarehouseService().addBook(2);
        warehouseController.getWarehouseService().addBook(1);

        //Удаление книг со склада
        warehouseController.getWarehouseService().removeBook(1);
        warehouseController.getWarehouseService().removeBook(1);

        System.out.println("------------------Сортировка книг по алфавиту-----------------");
        bookController.getBookService().sortByAlphabetical();
        System.out.println(bookController.getBookService().showBooks());

        System.out.println("------------------Сортировка книг по цене-----------------");
        bookController.getBookService().sortByPrice();
        System.out.println(bookController.getBookService().showBooks());

        System.out.println("------------------Сортировка книг по дате-----------------");
        bookController.getBookService().sortByDate();
        System.out.println(bookController.getBookService().showBooks());

        System.out.println("------------------Сортировка книг по статусу-----------------");
        bookController.getBookService().sortByStatus();
        System.out.println(bookController.getBookService().showBooks());

        //Описание
        System.out.println(bookController.getBookService().getDescription(1));

        //Добавление заказа
        orderController.getOrderService().addOrder(new Customer(), new int[] {1, 2, 3, 4});
        orderController.getOrderService().addOrder(new Customer(), new int[] {1, 2, 2, 2});
        orderController.getOrderService().addOrder(new Customer(), new int[] {4, 4, 2});
        orderController.getOrderService().addOrder(new Customer(), new int[] {2, 3, 4});
        orderController.getOrderService().addOrder(new Customer(), new int[] {3});

        //Отмена заказа
        orderController.getOrderService().removeOrder(1);
        orderController.getOrderService().removeOrder(2);

        //Статус заказа изменен
        orderController.getOrderService().setStatus(3, OrderStatus.COMPLETED);
        orderController.getOrderService().setStatus(4, OrderStatus.COMPLETED);

        //Сортировка заказа по цене
        System.out.println("------------------Сортировка заказов по цене-----------------");
        orderController.getOrderService().sortOrdersByPrice();
        System.out.println(orderController.getOrderService().showOrders());

        //Сортировка заказа по дате
        System.out.println("------------------Сортировка заказов по дате-----------------");
        orderController.getOrderService().sortOrdersByDate();
        System.out.println(orderController.getOrderService().showOrders());

        //Сортировка заказа по статусу
        System.out.println("------------------Сортировка заказов по статусу-----------------");
        orderController.getOrderService().sortOrdersByStatus();
        System.out.println(orderController.getOrderService().showOrders());

        //Сортировка выполненного заказа по цене
        System.out.println("------------------Сортировка заказов по цене-----------------");
        orderController.getOrderService().sortCompletedOrdersByPrice();
        System.out.println(orderController.getOrderService().showCompletedOrders(LocalDate.of(2010, Month.NOVEMBER, 22), LocalDate.of(2023, Month.NOVEMBER, 22)));

        //Сортировка выполненного заказа по дате
        System.out.println("------------------Сортировка заказов по дате-----------------");
        orderController.getOrderService().sortCompletedOrdersByDate();
        System.out.println(orderController.getOrderService().showCompletedOrders(LocalDate.of(2010, Month.NOVEMBER, 22), LocalDate.of(2023, Month.NOVEMBER, 22)));
        //Сумма выполненных заказов за период времени
        System.out.println("Сумма выполненных заказов");
        System.out.println(orderController.getOrderService().getFullPrice(LocalDate.of(2010, Month.NOVEMBER, 22), LocalDate.of(2023, Month.NOVEMBER, 22)));
        //Количество выполненных заказов за период времени
        System.out.println("Количество выполненных заказов");
        System.out.println(orderController.getOrderService().getQuantityCompletedOrders(LocalDate.of(2010, Month.NOVEMBER, 22), LocalDate.of(2023, Month.NOVEMBER, 22)));
    }
}
