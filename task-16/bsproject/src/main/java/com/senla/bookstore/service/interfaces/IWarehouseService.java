package com.senla.bookstore.service.interfaces;

import com.senla.bookstore.model.Warehouse;

import java.util.List;

public interface IWarehouseService {

    List<Warehouse> getSortedWarehouse(String key);

    List<Warehouse> getWarehouse();

    List<Warehouse> getStaleBooks(String key);

    void addBook(Integer bookId);

    void removeBook(Integer bookId);
}
