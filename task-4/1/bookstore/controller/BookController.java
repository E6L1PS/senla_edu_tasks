package ru.mirea.senla.bookstore.controller;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.service.BookService;

import java.util.List;

//@Controller
//@RequestMapping("/books")
public class BookController {
    //@Autowired
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public //@ResponseBody
    List<Book> getAllBooks() {
        return bookService.getBooks();
    }

}
