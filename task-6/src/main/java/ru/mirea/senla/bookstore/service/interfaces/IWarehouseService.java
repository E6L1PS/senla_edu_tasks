package ru.mirea.senla.bookstore.service.interfaces;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.repository.interfaces.IBookRepository;

import java.util.List;

public interface IWarehouseService {

    IBookRepository getWarehouseRepository();

    IBookRepository getBookRepository();

    void addBook(int bookId);

    void removeBook(int bookId);

    List<Book> getStaleBooks(String key);

}
