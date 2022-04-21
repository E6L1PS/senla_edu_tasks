package ru.mirea.senla.bookstore.model.menu;

import ru.mirea.senla.bookstore.controller.BookController;
import ru.mirea.senla.bookstore.controller.OrderController;
import ru.mirea.senla.bookstore.controller.WarehouseController;
import ru.mirea.senla.bookstore.model.*;
import ru.mirea.senla.bookstore.model.compares.ordercompares.CompareOrderByDate;
import ru.mirea.senla.bookstore.model.compares.ordercompares.CompareOrderByStatus;
import ru.mirea.senla.bookstore.model.json.JsonWriter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu extends Menu {

    private static final String name ="МЕНЮ";

    public MainMenu getThis() {
        return this;
    }

    public MainMenu(BookController bookController, OrderController orderController, WarehouseController warehouseController) {

        super(name, new MenuItem[] {
                new MenuItem("0. <--- Выход", () -> {
                    System.out.println("Программа завершена");
                    JsonWriter jsonWriter = new JsonWriter();
                    jsonWriter.write(warehouseController.getBookRepository(), "Library.json");
                    jsonWriter.write(warehouseController.getWarehouseRepository(), "Warehouse.json");
                    jsonWriter.write(orderController.getOrderRepository(), "Orders.json");
                    jsonWriter.write(orderController.getRequestRepository(), "Requests.json");
                },null),
                new MenuItem("1. Сервис книг", () -> System.out.println("\nВведите номер пункта"),
                        new Menu("Сервис книг", new MenuItem[] {
                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"),null),
                                new MenuItem("1. Сортировать книги по алфавиту", () -> System.out.println(bookController.getSortedBooks("BookByAlphabetical")), null),
                                new MenuItem("2. Сортировать книги по дате", () -> System.out.println(bookController.getSortedBooks("BookByDate")), null),
                                new MenuItem("3. Сортировать книги по цене", () -> System.out.println(bookController.getSortedBooks("BookByPrice")), null),
                                new MenuItem("4. Сортировать книги по наличию на складе", () -> System.out.println(bookController.getSortedBooks("BookByStatus")), null),
                                new MenuItem("5. Сортировать по id", () -> System.out.println(bookController.getBooks()), null),
                                new MenuItem("6. Импорт книг", () -> bookController.importBooks(), null),
                                new MenuItem("7. Экспорт книг", () -> bookController.exportBooks(), null),
                                new MenuItem("8. Экспорт книги", () -> bookController.exportBook(new Scanner(System.in).nextInt()), null),
                                new MenuItem("9. Посмотреть описание книги", () -> {
                                    System.out.println("Введите id книги");
                                    System.out.println(bookController.getDescription(new Scanner(System.in).nextInt()));
                                }, null),
                                new MenuItem("10. Запросы", () -> System.out.println("\nВведите номер пункта"),
                                        new Menu("Запросы", new MenuItem[] {
                                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"),null),
                                                new MenuItem("1. Оставить запрос на книгу", () -> bookController.addRequest(new Scanner(System.in).nextInt()),null),
                                                new MenuItem("2. Сортировать запросы по алфавиту", () -> System.out.println(bookController.getRequestBooks("RequestByAlphabetical")),null),
                                                new MenuItem("3. Сортировать запросы по количеству", () -> System.out.println(bookController.getRequestBooks("RequestByNumber")),null),
                                                new MenuItem("4. Импорт запросов", () -> bookController.exportBook(new Scanner(System.in).nextInt()), null),
                                                new MenuItem("5. Экспорт запросов", () -> bookController.exportBook(new Scanner(System.in).nextInt()), null),
                                                new MenuItem("6. Экспорт запроса", () -> bookController.exportBook(new Scanner(System.in).nextInt()), null)
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
                                new MenuItem("9. Экспорт заказа", () -> orderController.exportOrder(new Scanner(System.in).nextInt()), null),
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

                                                    System.out.println(orderController.getCompletedOrders(dateStart, dateEnd, new CompareOrderByDate()));
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

                                                    System.out.println(orderController.getCompletedOrders(dateStart, dateEnd, new CompareOrderByStatus()));
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
                        new Menu("Сервис склада", new MenuItem[] {
                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"),null),
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
        });
        System.out.println("\nВведите номер пункта");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
