package ru.mirea.senla.bookstore.model.menu;

import ru.mirea.senla.bookstore.model.*;
import ru.mirea.senla.bookstore.model.compares.bookcompares.CompareBookByAlphabetical;
import ru.mirea.senla.bookstore.model.compares.bookcompares.CompareBookByDate;
import ru.mirea.senla.bookstore.model.compares.bookcompares.CompareBookByPrice;
import ru.mirea.senla.bookstore.model.compares.ordercompares.CompareOrderByDate;
import ru.mirea.senla.bookstore.model.compares.ordercompares.CompareOrderByPrice;
import ru.mirea.senla.bookstore.model.compares.ordercompares.CompareOrderByStatus;
import ru.mirea.senla.bookstore.model.compares.requestcompares.CompareRequestByAlphabetical;
import ru.mirea.senla.bookstore.model.compares.requestcompares.CompareRequestByNumber;
import ru.mirea.senla.bookstore.service.BookService;
import ru.mirea.senla.bookstore.service.OrderService;
import ru.mirea.senla.bookstore.service.WarehouseService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu extends Menu {

    private static final String name ="МЕНЮ";

    public MainMenu getThis() {
        return this;
    }

    public MainMenu(BookService bookService, OrderService orderService, WarehouseService warehouseService) {

        super(name, new MenuItem[] {
                new MenuItem("0. <--- Выход", () -> System.out.println("Программа завершена"),null),
                new MenuItem("1. Сервис книг", () -> System.out.println("\nВведите номер пункта"),
                        new Menu("Сервис книг", new MenuItem[] {
                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"),null),
                                new MenuItem("1. Сортировать книги по алфавиту", () -> System.out.println(bookService.getSortedBooks(new CompareBookByAlphabetical())), null),
                                new MenuItem("2. Сортировать книги по дате", () -> System.out.println(bookService.getSortedBooks(new CompareBookByDate())), null),
                                new MenuItem("3. Сортировать книги по цене", () -> System.out.println(bookService.getSortedBooks(new CompareBookByPrice())), null),
                                new MenuItem("4. Сортировать книги по наличию на складе", () -> System.out.println(bookService.getSortedBooks(new CompareBookByDate())), null),
                                new MenuItem("5. Посмотреть описание книги", () -> {
                                    System.out.println("Введите id книги");
                                    System.out.println(bookService.getDescription(new Scanner(System.in).nextInt()));
                                }, null),
                                new MenuItem("6. Запросы", () -> System.out.println("\nВведите номер пункта"),
                                        new Menu("Запросы", new MenuItem[] {
                                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"),null),
                                                new MenuItem("1. Оставить запрос на книгу", () -> bookService.addRequest(new Scanner(System.in).nextInt()),null),
                                                new MenuItem("2. Сортировать запросы по алфавиту", () -> System.out.println(bookService.getRequestBooks(new CompareRequestByAlphabetical())),null),
                                                new MenuItem("3. Сортировать запросы по количеству", () -> System.out.println(bookService.getRequestBooks(new CompareRequestByNumber())),null)
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

                                    orderService.addOrder(null, bookIds);
                                }, null),
                                new MenuItem("2. Отменить заказ", () -> {
                                    System.out.println("Введите id заказа");
                                    orderService.removeOrder(new Scanner(System.in).nextInt());
                                }, null),
                                new MenuItem("3. Изменить статус заказа на 'выполненный'", () -> {
                                    Scanner sc = new Scanner(System.in);
                                    System.out.println("Введите id заказа");
                                    orderService.setStatus(sc.nextInt(), OrderStatus.COMPLETED);
                                }, null),
                                new MenuItem("4. Сортировать заказы по дате исполнения", () -> System.out.println(orderService.getSortedOrders(new CompareOrderByDate())), null),
                                new MenuItem("5. Сортировать заказы по цене", () -> System.out.println(orderService.getSortedOrders(new CompareOrderByPrice())), null),
                                new MenuItem("6. Сортировать заказы по статусу", () -> System.out.println(orderService.getSortedOrders(new CompareOrderByStatus())), null),
                                new MenuItem("7. Выполненные заказы", () -> System.out.println("\nВведите номер пункта"),
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

                                                    System.out.println(orderService.getCompletedOrders(dateStart, dateEnd, new CompareOrderByDate()));
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

                                                    System.out.println(orderService.getCompletedOrders(dateStart, dateEnd, new CompareOrderByStatus()));
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

                                                    System.out.println("Сумма выполненных заказов " + orderService.getFullPrice(dateStart, dateEnd));
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

                                                    System.out.println("Количество выполненных заказов " + orderService.getQuantityCompletedOrders(dateStart, dateEnd));
                                                }, null)
                                        }))
                       })),

                new MenuItem("3. Сервис склада", () -> System.out.println("\nВведите номер пункта"),
                        new Menu("Сервис склада", new MenuItem[] {
                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"),null),
                                new MenuItem("1. Добавить книгу на склад", () -> {
                                    System.out.println("Введите id книги");
                                    warehouseService.addBook(new Scanner(System.in).nextInt());
                                }, null),
                                new MenuItem("2. Снять книгу со склада", () -> {
                                    System.out.println("Введите id книги");
                                    warehouseService.removeBook(new Scanner(System.in).nextInt());
                                }, null),
                                new MenuItem("3. Показать книги со склада", () -> System.out.println(warehouseService.getWarehouseRepository().getBooks()), null),
                                new MenuItem("4. Залежавшие книги", () -> System.out.println("\nВведите номер пункта"),
                                        new Menu("Залежавшие книги", new MenuItem[]{
                                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"), null),
                                                new MenuItem("1. Сортировать залежавшие книги по дате", () -> System.out.println(warehouseService.getStaleBooks(new CompareBookByDate())), null),
                                                new MenuItem("2. Сортировать залежавшие книги по цене", () -> System.out.println(warehouseService.getStaleBooks(new CompareBookByPrice())), null),

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
