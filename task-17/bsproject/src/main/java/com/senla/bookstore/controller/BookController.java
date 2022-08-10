package com.senla.bookstore.controller;

import com.senla.bookstore.dto.BookDto;
import com.senla.bookstore.model.Author;
import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Request;
import com.senla.bookstore.service.interfaces.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;


    @GetMapping
    public List<BookDto> getSortedBooks(@RequestParam(defaultValue = "id") String key) {
        log.info("Method call 'getSortedBooks'");
        return bookService.getSortedBooks(key)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/requests")
    public List<Request> getRequestBooks(@RequestParam(defaultValue = "id") String key) {
        log.info("Method call 'getRequestBooks'");
        return bookService.getRequestBooks(key);
    }

    @GetMapping("/description/{id}")
    public String getDescription(@PathVariable("id") int id) {
        log.info("Method call 'getDescription'");
        return bookService.getDescription(id);
    }

    @GetMapping("/authors")
    public List<Author> getAuthors() {
        log.info("Method call 'getAuthors'");
        return bookService.getAuthors();
    }

    //TODO imp and export
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


    private BookDto convertEntityToDTO(Book book) {
        BookDto bookDTO = new BookDto();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setStatus(book.getStatus());
        bookDTO.setAuthors(book.getAuthors());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setPublicationDate(book.getPublicationDate());
        return bookDTO;
    }
}
