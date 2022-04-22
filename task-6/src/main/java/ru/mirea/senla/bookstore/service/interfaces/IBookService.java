package ru.mirea.senla.bookstore.service.interfaces;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.model.Request;

import java.util.List;

public interface IBookService {

    List<Book> getBooks();

    List<Book> getSortedBooks(String key);

    String getDescription(int id);

    List<Request> getRequestBooks(String key);

    void addRequest(int bookId);

    void exportBook(int bookId);

    void exportBooks();

    void importBooks();
}
