package ru.mirea.senla.bookstore.controller;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.repository.interfaces.IBookRepository;
import ru.mirea.senla.bookstore.service.interfaces.IWarehouseService;

import java.util.List;

public class WarehouseController {

    private IWarehouseService warehouseService;

    public WarehouseController(IWarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public IWarehouseService getWarehouseService() {
        return warehouseService;
    }

    public IBookRepository  getWarehouseRepository() {
        return warehouseService.getWarehouseRepository();
    }

    public IBookRepository getBookRepository() {
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
