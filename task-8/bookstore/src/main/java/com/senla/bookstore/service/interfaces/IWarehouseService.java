package com.senla.bookstore.service.interfaces;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.repository.interfaces.IBookRepository;

import java.util.List;

public interface IWarehouseService {

    IBookRepository getWarehouseRepository();

    IBookRepository getBookRepository();

    void addBook(int bookId);

    void removeBook(int bookId);

    List<Book> getStaleBooks(String key);

}
