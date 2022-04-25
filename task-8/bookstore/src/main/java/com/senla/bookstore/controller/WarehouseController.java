package com.senla.bookstore.controller;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import com.senla.bookstore.service.interfaces.IWarehouseService;
import com.senla.configure.annotations.Autowired;
import com.senla.configure.annotations.Singleton;

import java.util.List;

@Singleton
public class WarehouseController {

    @Autowired
    private IWarehouseService warehouseService;

    public WarehouseController() {

    }

    public IWarehouseService getWarehouseService() {
        return warehouseService;
    }

    public IBookRepository getWarehouseRepository() {
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
