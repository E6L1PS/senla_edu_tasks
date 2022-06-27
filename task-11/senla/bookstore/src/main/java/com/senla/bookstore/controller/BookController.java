package com.senla.bookstore.controller;

import com.senla.bookstore.model.Author;
import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Request;
import com.senla.bookstore.service.interfaces.IBookService;
import com.senla.configure.annotations.Autowired;
import com.senla.configure.annotations.Singleton;

import java.util.List;

@Singleton
public class BookController {

    @Autowired
    private IBookService bookService;

    public BookController() {

    }

    public IBookService getBookService() {
        return bookService;
    }

    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    public List<Book> getSortedBooks(String key) {
        return bookService.getSortedBooks(key);
    }

    public String getDescription(int id) {
        return bookService.getDescription(id);
    }

    public List<Request> getRequestBooks(String key) {
        return bookService.getRequestBooks(key);
    }

    public List<Author> getAuthors() {
        return bookService.getAuthors();
    }
    public void addRequest(int bookId) {
        bookService.addRequest(bookId);
    }

    public void exportBook(int bookId) {
        bookService.exportBook(bookId);
    }

    public void exportBooks() {
        bookService.exportBooks();
    }

    public void importBooks() {
        bookService.importBooks();
    }

}
