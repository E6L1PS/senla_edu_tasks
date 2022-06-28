package com.senla.bookstore.service.interfaces;

import com.senla.bookstore.model.Author;
import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Request;

import java.util.List;

public interface IBookService {

    List<Book> getBooks();

    List<Book> getSortedBooks(String key);

    String getDescription(int id);

    List<Request> getRequestBooks(String key);

    List<Author> getAuthors();

    void addRequest(int bookId);

    void exportBook(int bookId);

    void exportBooks();

    void importBooks();
}
