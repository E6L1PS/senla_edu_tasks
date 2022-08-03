package com.senla.bookstore.service.interfaces;

import com.senla.bookstore.model.Warehouse;
import com.senla.bookstore.repository.CustomerRepository;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import com.senla.bookstore.repository.interfaces.IWarehouseRepository;

import java.util.List;

public interface IWarehouseService {

    IWarehouseRepository getWarehouseRepository();

    IBookRepository getBookRepository();

    CustomerRepository getCustomerRepository();

    List<Warehouse> getSortedWarehouse(String key);

    List<Warehouse> getWarehouse();

    List<Warehouse> getStaleBooks(String key);

    void addBook(Integer bookId);

    void removeBook(Integer bookId);
}
