package com.senla.bookstore.view.menu;

import com.senla.bookstore.controller.BookController;
import com.senla.bookstore.controller.OrderController;
import com.senla.bookstore.controller.WarehouseController;
import com.senla.bookstore.model.OrderStatus;
import com.senla.bookstore.util.json.JsonWriter;
import com.senla.configure.annotations.Autowired;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

    @Autowired
    private BookController bookController;

    @Autowired
    private OrderController orderController;

    @Autowired
    private WarehouseController warehouseController;

    private String name;

    private MenuItem[] menuItems;

    public Menu() {

    }

    public Menu(String name, MenuItem[] menuItems) {
        this.name = name;
        this.menuItems = menuItems;
    }

    @PostConstruct
    public void init() {
        this.name = "МЕНЮ";
        this.menuItems = new MenuItem[]{
                new MenuItem("0. <--- Выход", () -> {
                    System.out.println("Программа завершена");
                    JsonWriter.write(warehouseController.getBookRepository().getBooks(), "Library.json");
                    JsonWriter.write(warehouseController.getWarehouseRepository().getBooks(), "Warehouse.json");
                    JsonWriter.write(orderController.getOrderRepository().getOrders(), "Orders.json");
                    JsonWriter.write(orderController.getRequestRepository().getRequests(), "Requests.json");
                }, null),
                new MenuItem("1. Сервис книг", () -> System.out.println("\nВведите номер пункта"),
                        new Menu("Сервис книг", new MenuItem[]{
                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"), null),
                                new MenuItem("1. Сортировать книги по алфавиту", () -> System.out.println(bookController.getSortedBooks("BookByAlphabetical")), null),
                                new MenuItem("2. Сортировать книги по дате", () -> System.out.println(bookController.getSortedBooks("BookByDate")), null),
                                new MenuItem("3. Сортировать книги по цене", () -> System.out.println(bookController.getSortedBooks("BookByPrice")), null),
                                new MenuItem("4. Сортировать книги по наличию на складе", () -> System.out.println(bookController.getSortedBooks("BookByStatus")), null),
                                new MenuItem("5. Сортировать по id", () -> System.out.println(bookController.getBooks()), null),
                                new MenuItem("6. Импорт книг", () -> bookController.importBooks(), null),
                                new MenuItem("7. Экспорт книг", () -> bookController.exportBooks(), null),
                                new MenuItem("8. Экспорт книги", () -> {
                                    System.out.println("Введите id книги, которую хотите экспортировать");
                                    bookController.exportBook(new Scanner(System.in).nextInt());
                                }, null),
                                new MenuItem("9. Посмотреть описание книги", () -> {
                                    System.out.println("Введите id книги");
                                    System.out.println(bookController.getDescription(new Scanner(System.in).nextInt()));
                                }, null),
                                new MenuItem("10. Запросы", () -> System.out.println("\nВведите номер пункта"),
                                        new Menu("Запросы", new MenuItem[]{
                                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"), null),
                                                new MenuItem("1. Оставить запрос на книгу", () -> bookController.addRequest(new Scanner(System.in).nextInt()), null),
                                                new MenuItem("2. Сортировать запросы по алфавиту", () -> System.out.println(bookController.getRequestBooks("RequestByAlphabetical")), null),
                                                new MenuItem("3. Сортировать запросы по количеству", () -> System.out.println(bookController.getRequestBooks("RequestByNumber")), null)
                                        }))
                        })),

                new MenuItem("2. Сервис заказов", () -> System.out.println("\nВведите номер пункта"),
                        new Menu("Сервис заказов", new MenuItem[]{
                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"), null),
                                new MenuItem("1. Создать заказ", () -> {
                                    Scanner sc = new Scanner(System.in);

                                    System.out.println("Введите количество книг");
                                    int n = sc.nextInt();

                                    List<Integer> bookIds = new ArrayList<>();

                                    System.out.println("Введите id книг");
                                    for (int i = 0; i < n; i++) {
                                        bookIds.add(sc.nextInt());
                                    }

                                    orderController.addOrder(null, bookIds);
                                }, null),
                                new MenuItem("2. Отменить заказ", () -> {
                                    System.out.println("Введите id заказа");
                                    orderController.removeOrder(new Scanner(System.in).nextInt());
                                }, null),
                                new MenuItem("3. Изменить статус заказа на 'выполненный'", () -> {
                                    Scanner sc = new Scanner(System.in);
                                    System.out.println("Введите id заказа");
                                    orderController.setStatus(sc.nextInt(), OrderStatus.COMPLETED);
                                }, null),
                                new MenuItem("4. Сортировать заказы по дате исполнения", () -> System.out.println(orderController.getSortedOrders("OrderByDate")), null),
                                new MenuItem("5. Сортировать заказы по цене", () -> System.out.println(orderController.getSortedOrders("OrderByPrice")), null),
                                new MenuItem("6. Сортировать заказы по статусу", () -> System.out.println(orderController.getSortedOrders("OrderByStatus")), null),
                                new MenuItem("7. Импорт заказов", () -> orderController.importOrders(), null),
                                new MenuItem("8. Экспорт заказов", () -> orderController.exportOrders(), null),
                                new MenuItem("9. Экспорт заказа", () -> {
                                    System.out.println("Введите id заказа, который хотите экспортировать");
                                    orderController.exportOrder(new Scanner(System.in).nextInt());
                                }, null),
                                new MenuItem("10. Выполненные заказы", () -> System.out.println("\nВведите номер пункта"),
                                        new Menu("Выполненные заказы", new MenuItem[]{
                                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"), null),
                                                new MenuItem("1. Сортировать выполненные заказы по дате", () -> {
                                                    Scanner sc = new Scanner(System.in);
                                                    System.out.println("Напишите дату с какого");
                                                    System.out.println("День:");
                                                    int day = sc.nextInt();
                                                    System.out.println("Месяц:");
                                                    int mouth = sc.nextInt();
                                                    System.out.println("Год:");
                                                    int year = sc.nextInt();
                                                    LocalDate dateStart = LocalDate.of(year, mouth, day);

                                                    System.out.println("Напишите дату по какой");
                                                    System.out.println("День:");
                                                    day = sc.nextInt();
                                                    System.out.println("Месяц:");
                                                    mouth = sc.nextInt();
                                                    System.out.println("Год:");
                                                    year = sc.nextInt();
                                                    LocalDate dateEnd = LocalDate.of(year, mouth, day);

                                                    System.out.println(orderController.getCompletedOrders(dateStart, dateEnd, "OrderByDate"));
                                                }, null),
                                                new MenuItem("2. Сортировать выполненные заказы по цене", () -> {
                                                    Scanner sc = new Scanner(System.in);
                                                    System.out.println("Напишите дату с какого");
                                                    System.out.println("День:");
                                                    int day = sc.nextInt();
                                                    System.out.println("Месяц:");
                                                    int mouth = sc.nextInt();
                                                    System.out.println("Год:");
                                                    int year = sc.nextInt();
                                                    LocalDate dateStart = LocalDate.of(year, mouth, day);

                                                    System.out.println("Напишите дату по какой");
                                                    System.out.println("День:");
                                                    day = sc.nextInt();
                                                    System.out.println("Месяц:");
                                                    mouth = sc.nextInt();
                                                    System.out.println("Год:");
                                                    year = sc.nextInt();
                                                    LocalDate dateEnd = LocalDate.of(year, mouth, day);

                                                    System.out.println(orderController.getCompletedOrders(dateStart, dateEnd, "OrderByPrice"));
                                                }, null),
                                                new MenuItem("3. Показать сумму заработанных средств за период времени", () -> {
                                                    Scanner sc = new Scanner(System.in);
                                                    System.out.println("Напишите дату с какого");
                                                    System.out.println("День:");
                                                    int day = sc.nextInt();
                                                    System.out.println("Месяц:");
                                                    int mouth = sc.nextInt();
                                                    System.out.println("Год:");
                                                    int year = sc.nextInt();
                                                    LocalDate dateStart = LocalDate.of(year, mouth, day);

                                                    System.out.println("Напишите дату по какой");
                                                    System.out.println("День:");
                                                    day = sc.nextInt();
                                                    System.out.println("Месяц:");
                                                    mouth = sc.nextInt();
                                                    System.out.println("Год:");
                                                    year = sc.nextInt();
                                                    LocalDate dateEnd = LocalDate.of(year, mouth, day);

                                                    System.out.println("Сумма выполненных заказов " + orderController.getFullPrice(dateStart, dateEnd));
                                                }, null),
                                                new MenuItem("4. Показать количество выполненных заказов за перод времени", () -> {
                                                    Scanner sc = new Scanner(System.in);
                                                    System.out.println("Напишите дату с какого");

                                                    System.out.println("День:");
                                                    int day = sc.nextInt();
                                                    System.out.println("Месяц:");
                                                    int mouth = sc.nextInt();
                                                    System.out.println("Год:");
                                                    int year = sc.nextInt();
                                                    LocalDate dateStart = LocalDate.of(year, mouth, day);

                                                    System.out.println("Напишите дату по какой");
                                                    System.out.println("День:");
                                                    day = sc.nextInt();
                                                    System.out.println("Месяц:");
                                                    mouth = sc.nextInt();
                                                    System.out.println("Год:");
                                                    year = sc.nextInt();
                                                    LocalDate dateEnd = LocalDate.of(year, mouth, day);

                                                    System.out.println("Количество выполненных заказов " + orderController.getQuantityCompletedOrders(dateStart, dateEnd));
                                                }, null)
                                        }))
                        })),

                new MenuItem("3. Сервис склада", () -> System.out.println("\nВведите номер пункта"),
                        new Menu("Сервис склада", new MenuItem[]{
                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"), null),
                                new MenuItem("1. Добавить книгу на склад", () -> {
                                    System.out.println("Введите id книги");
                                    warehouseController.addBook(new Scanner(System.in).nextInt());
                                }, null),
                                new MenuItem("2. Снять книгу со склада", () -> {
                                    System.out.println("Введите id книги");
                                    warehouseController.removeBook(new Scanner(System.in).nextInt());
                                }, null),
                                new MenuItem("3. Показать книги со склада", () -> System.out.println(warehouseController.getWarehouseRepository().getBooks()), null),
                                new MenuItem("4. Залежавшие книги", () -> System.out.println("\nВведите номер пункта"),
                                        new Menu("Залежавшие книги", new MenuItem[]{
                                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"), null),
                                                new MenuItem("1. Сортировать залежавшие книги по дате", () -> System.out.println(warehouseController.getStaleBooks("WarehouseByDate")), null),
                                                new MenuItem("2. Сортировать залежавшие книги по цене", () -> System.out.println(warehouseController.getStaleBooks("WarehouseByPrice")), null),

                                        }))
                        }))
        };
    }

    public Menu getThis() {
        return this;
    }

    public String getName() {
        return name;
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenuItems(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String toString() {
        return "----------" + name + "----------\n" +
                Arrays.toString(menuItems)
                        .replaceAll(", ", "")
                        .replace("[", "")
                        .replace("]", "");
    }

}
