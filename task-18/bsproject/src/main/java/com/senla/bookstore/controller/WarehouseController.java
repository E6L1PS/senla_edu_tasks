package com.senla.bookstore.controller;

import com.senla.bookstore.model.Warehouse;
import com.senla.bookstore.service.interfaces.IWarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private IWarehouseService warehouseService;

    @GetMapping
    public List<Warehouse> getSortedWarehouse(@RequestParam(defaultValue = "id") String key) {
        return warehouseService.getSortedWarehouse(key);
    }

    @GetMapping("/stale")
    public List<Warehouse> getStaleBooks(@RequestParam(defaultValue = "id") String key) {
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


