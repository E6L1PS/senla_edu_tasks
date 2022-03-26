package ru.mirea.senla.bookstore.service;

import ru.mirea.senla.bookstore.model.BookStatus;
import ru.mirea.senla.bookstore.repository.BookRepository;
import ru.mirea.senla.bookstore.repository.WarehouseRepository;

import java.time.LocalDate;

public class WarehouseService {
    WarehouseRepository warehouseRepository;
    BookRepository bookRepository;

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
        bookRepository.getBooks().get(bookId).setStatus(BookStatus.IN_STOCK);
        bookRepository.getBooks().get(bookId).setDeliveryDate(LocalDate.now());
        warehouseRepository.getBooks().add(bookRepository.getBooks().get(bookId));
        System.out.println("Добавлена книга '" + bookId  + "' на склад, статус книги: " + bookRepository.getBooks().get(bookId).getStatus());
        bookRepository.getBooks().get(bookId).getRequest().clear();//закрывает все реквесты
    }

    public void removeBook(int bookId) {
        warehouseRepository.getBooks().remove(bookRepository.getBooks().get(bookId));
        if (warehouseRepository.getBooks().stream().anyMatch(x -> x.getId() == bookId)) {
            bookRepository.getBooks().get(bookId).setStatus(BookStatus.IN_STOCK);
        } else {
            bookRepository.getBooks().get(bookId).setStatus(BookStatus.OUT_STOCK);
        }

        System.out.println("Снята книга '" + bookId + "' со склада, статус книги: " + bookRepository.getBooks().get(bookId).getStatus());
    }
}
