package ru.mirea.senla.bookstore.repository.interfaces;

import ru.mirea.senla.bookstore.model.Book;

import java.util.List;

public interface IBookRepository {

    Book getBookById(int id);

    List<Book> getBooks();

    void addBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Book book);

    void deleteBookById(int id);

}
