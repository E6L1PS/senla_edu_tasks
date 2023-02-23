package ru.mirea.senla.bookstore.controller;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.model.Request;
import ru.mirea.senla.bookstore.service.interfaces.IBookService;

import java.util.List;

public class BookController {

    private IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
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