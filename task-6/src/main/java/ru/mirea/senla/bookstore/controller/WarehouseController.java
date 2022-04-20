package ru.mirea.senla.bookstore.controller;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.repository.BookRepository;
import ru.mirea.senla.bookstore.repository.WarehouseRepository;
import ru.mirea.senla.bookstore.service.WarehouseService;
import java.util.List;

public class WarehouseController {

    private WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public WarehouseService getWarehouseService() {
        return warehouseService;
    }

    public WarehouseRepository getWarehouseRepository() {
        return warehouseService.getWarehouseRepository();
    }

    public BookRepository getBookRepository() {
        return warehouseService.getBookRepository();
    }

    public void addBook(int bookId) {
        warehouseService.addBook(bookId);
    }

    public void removeBook(int bookId) {
        warehouseService.removeBook(bookId);
    }

    public List<Book> getStaleBooks(String key) {
        return warehouseService.getStaleBooks(key);
    }
}
