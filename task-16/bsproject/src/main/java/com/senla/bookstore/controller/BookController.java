package com.senla.bookstore.controller;

import com.senla.bookstore.dto.BookDTO;
import com.senla.bookstore.model.Author;
import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Request;
import com.senla.bookstore.service.interfaces.IBookService;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@NoArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public List<BookDTO> getBooks() {
        log.info("Method call 'getBooks'");
        return bookService.getBooks()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @RequestMapping("/sort/{key}")
    public List<BookDTO> getSortedBooks(@PathVariable("key") String key) {
        log.info("Method call 'getSortedBooks'");
        return bookService.getSortedBooks(key)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @RequestMapping("/description/{id}")
    public String getDescription(@PathVariable("id") int id) {
        log.info("Method call 'getDescription'");
        return bookService.getDescription(id);
    }

    @RequestMapping("/requests/{key}")
    public List<Request> getRequestBooks(@PathVariable("key") String key) {
        log.info("Method call 'getRequestBooks'");
        return bookService.getRequestBooks(key);
    }

    @RequestMapping("/authors")
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


    private BookDTO convertEntityToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
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
