package com.senla.bookstore.controller;

import com.senla.bookstore.model.Warehouse;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import com.senla.bookstore.repository.interfaces.IWarehouseRepository;
import com.senla.bookstore.service.interfaces.IWarehouseService;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@NoArgsConstructor
@RestController
@RequestMapping("/warehouse")
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


    @GetMapping
    public List<Warehouse> getWarehouse() {
        return warehouseService.getWarehouse();
    }

    @GetMapping("/{key}")
    public List<Warehouse> getSortedWarehouse(@PathVariable String key) {
        return warehouseService.getSortedWarehouse(key);
    }

    @GetMapping("/stale/{key}")
    public List<Warehouse> getStaleBooks(@PathVariable String key) {
        return warehouseService.getStaleBooks(key);
    }

    @PostMapping("/add/{bookId}")
    public void addBook(@PathVariable int bookId) {
        warehouseService.addBook(bookId);
    }


    @DeleteMapping("/delete/{bookId}")
    public void removeBook(@PathVariable int bookId) {
        warehouseService.removeBook(bookId);
    }

}


