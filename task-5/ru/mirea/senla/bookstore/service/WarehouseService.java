package ru.mirea.senla.bookstore.service;

import ru.mirea.senla.bookstore.model.BookStatus;
import ru.mirea.senla.bookstore.repository.BookRepository;
import ru.mirea.senla.bookstore.repository.WarehouseRepository;

import java.time.LocalDate;

public class WarehouseService {

    private WarehouseRepository warehouseRepository;
    private BookRepository bookRepository;

    public WarehouseService(WarehouseRepository warehouseRepository, BookRepository bookRepository) {
        this.warehouseRepository = warehouseRepository;
        this.bookRepository = bookRepository;
    }

    public WarehouseRepository getWarehouseRepository() {
        return warehouseRepository;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void addBook(int bookId) {
        bookRepository.getBookById(bookId).setStatus(BookStatus.IN_STOCK);
        bookRepository.getBookById(bookId).setDeliveryDate(LocalDate.now());
        warehouseRepository.addBook(bookRepository.getBookById(bookId));
        System.out.println("Добавлена книга '" + bookId  + "' на склад, статус книги: " + bookRepository.getBookById(bookId).getStatus());
        bookRepository.getBookById(bookId).clearRequest();
    }

    public void removeBook(int bookId) {
        warehouseRepository.removeBook(bookRepository.getBookById(bookId));

        if (warehouseRepository.getBooks().stream().anyMatch(x -> x.getId() == bookId)) {
            bookRepository.getBookById(bookId).setStatus(BookStatus.IN_STOCK);
        } else {
            bookRepository.getBookById(bookId).setStatus(BookStatus.OUT_STOCK);
        }

        System.out.println("Снята книга '" + bookId + "' со склада, статус книги: " + bookRepository.getBookById(bookId).getStatus());
    }
}
