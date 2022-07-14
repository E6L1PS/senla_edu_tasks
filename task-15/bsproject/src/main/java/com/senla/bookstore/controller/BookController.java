package com.senla.bookstore.controller;

import com.senla.bookstore.model.Author;
import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Request;
import com.senla.bookstore.service.interfaces.IBookService;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Log4j2
@NoArgsConstructor
@Controller
public class BookController {

    @Autowired
    private IBookService bookService;

    public IBookService getBookService() {
        return bookService;
    }

    public List<Book> getBooks() {
        log.info("Method call 'getBooks'");
        return bookService.getBooks();
    }

    public List<Book> getSortedBooks(String key) {
        log.info("Method call 'getSortedBooks'");
        return bookService.getSortedBooks(key);
    }

    public String getDescription(int id) {
        log.info("Method call 'getDescription'");
        return bookService.getDescription(id);
    }

    public List<Request> getRequestBooks(String key) {
        log.info("Method call 'getRequestBooks'");
        return bookService.getRequestBooks(key);
    }

    public List<Author> getAuthors() {
        log.info("Method call 'getAuthors'");
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
