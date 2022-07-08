package com.senla.bookstore.controller;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import com.senla.bookstore.repository.interfaces.IWarehouseRepository;
import com.senla.bookstore.service.interfaces.IWarehouseService;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Log4j2
@NoArgsConstructor
@Controller
public class WarehouseController {

    @Autowired
    private IWarehouseService warehouseService;

    public IWarehouseService getWarehouseService() {
        return warehouseService;
    }

    public IWarehouseRepository getWarehouseRepository() {
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


