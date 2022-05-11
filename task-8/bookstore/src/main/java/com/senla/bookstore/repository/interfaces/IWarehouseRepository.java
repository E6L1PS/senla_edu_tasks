package com.senla.bookstore.repository.interfaces;

import com.senla.bookstore.model.Book;

import java.util.List;

public interface IWarehouseRepository {

    Book getBookById(int id);

    List<Book> getBooks();

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(Book book);

    void deleteBookById(int id);
}

