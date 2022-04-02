package ru.mirea.senla.bookstore.model.menu;

import ru.mirea.senla.bookstore.model.OrderStatus;
import ru.mirea.senla.bookstore.service.BookService;
import ru.mirea.senla.bookstore.service.OrderService;
import ru.mirea.senla.bookstore.service.WarehouseService;

import java.time.LocalDate;
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
                                new MenuItem("1. Показать книги", () -> System.out.println(bookService.showBooks()), null),
                                new MenuItem("2. Сортировать книги по алфавиту", () -> bookService.sortByAlphabetical(), null),
                                new MenuItem("3. Сортировать книги по дате", () -> bookService.sortByDate(), null),
                                new MenuItem("4. Сортировать книги по цене", () -> bookService.sortByPrice(), null),
                                new MenuItem("5. Сортировать книги по наличию на складе", () -> bookService.sortByStatus(), null),
                                new MenuItem("6. Посмотреть описание книги", () -> {
                                    System.out.println("Введите id книги");
                                    System.out.println(bookService.getDescription(new Scanner(System.in).nextInt()));
                                }, null),
                                new MenuItem("7. Залежавшие книги", () -> System.out.println("\nВведите номер пункта"),
                                        new Menu("Залежавшие книги", new MenuItem[]{
                                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"), null),
                                                new MenuItem("1. Показать залежавшие книги", () -> {
                                                    bookService.setStaleBooks();
                                                    System.out.println(bookService.showStaleBooks());
                                                }, null),
                                                new MenuItem("2. Сортировать залежавшие книги по дате", () -> bookService.sortStaleBooksByDate(), null),
                                                new MenuItem("3. Сортировать залежавшие книги по цене", () -> bookService.sortStaleBooksByPrice(), null),

                                        })),
                                new MenuItem("8. Запросы", () -> System.out.println("\nВведите номер пункта"),
                                        new Menu("Запросы", new MenuItem[] {
                                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"),null),
                                                new MenuItem("1. Оставить запрос на книгу", () -> bookService.addRequest(new Scanner(System.in).nextInt()),null),
                                                new MenuItem("2. Просмотреть запросы", () -> System.out.println(bookService.showRequestBooks()),null),
                                                new MenuItem("3. Сортировать запросы по алфавиту", () -> bookService.sortByAlphabetical(),null),
                                                new MenuItem("4. Сортировать запросы по количеству", () -> bookService.sortByRequestNumber(),null)
                                        })),
                        })),

                new MenuItem("2. Сервис заказов", () -> System.out.println("\nВведите номер пункта"),
                        new Menu("Сервис заказов", new MenuItem[]{
                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"), null),
                                new MenuItem("1. Создать заказ", () -> {
                                    Scanner sc = new Scanner(System.in);

                                    System.out.println("Введите количество книг");
                                    int[] bookIds = new int[sc.nextInt()];

                                    System.out.println("Введите id книг");
                                    for (int i = 0; i < bookIds.length; i++) {
                                        bookIds[i] = sc.nextInt();
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
                                new MenuItem("4. Показать заказы", () -> System.out.println(orderService.showOrders()), null),
                                new MenuItem("5. Сортировать заказы по дате исполнения", () -> orderService.sortOrdersByStatus(), null),
                                new MenuItem("6. Сортировать заказы по цене", () -> orderService.sortOrdersByPrice(), null),
                                new MenuItem("7. Сортировать заказы по статусу", () -> orderService.sortOrdersByStatus(), null),
                                new MenuItem("8. Выполненные заказы", () -> System.out.println("\nВведите номер пункта"),
                                        new Menu("Выполненные заказы", new MenuItem[]{
                                                new MenuItem("0. <--- Назад", () -> System.out.println("\nВведите номер пункта"), null),
                                                new MenuItem("1. Показать выполненные заказы за период времени", () -> {
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

                                                    System.out.println(orderService.showCompletedOrders(dateStart, dateEnd));
                                                }, null),
                                                new MenuItem("2. Сортировать выполненные заказы по дате", () -> orderService.sortCompletedOrdersByDate(), null),
                                                new MenuItem("3. Сортировать выполненные заказы по цене", () -> orderService.sortCompletedOrdersByPrice(), null),
                                                new MenuItem("4. Показать сумму заработанных средств за период времени", () -> {
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
                                                new MenuItem("5. Показать количество выполненных заказов за перод времени", () -> {
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
                        }))
        });
        System.out.println("\nВведите номер пункта");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
