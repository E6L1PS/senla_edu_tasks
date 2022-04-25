package com.senla.bookstore.service;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.BookStatus;
import com.senla.bookstore.model.Request;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import com.senla.bookstore.repository.interfaces.IRequestRepository;
import com.senla.bookstore.service.interfaces.IBookService;
import com.senla.bookstore.model.compares.CompareStrategy;
import com.senla.bookstore.util.csv.CsvReader;
import com.senla.bookstore.util.csv.CsvWriter;
import com.senla.configure.annotations.Autowired;
import com.senla.configure.annotations.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IRequestRepository requestRepository;

    public BookService() {

    }

    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }

    public List<Book> getSortedBooks(String sortType) {
        List<Book> sortedBooks = new ArrayList<>(bookRepository.getBooks());
        sortedBooks.sort(new CompareStrategy().getComparator(sortType));
        return sortedBooks;
    }

    public String getDescription(int id) {
        return bookRepository.getBookById(id).getDescription();
    }

    public List<Request> getRequestBooks(String sortType) {
        List<Request> sortedRequests = new ArrayList<>(requestRepository.getRequests());
        sortedRequests.sort(new CompareStrategy().getComparator(sortType));
        return sortedRequests;
    }

    public void addRequest(int bookId) {
        Book book = bookRepository.getBookById(bookId);
        if (book.getStatus() == BookStatus.OUT_STOCK) {
            requestRepository.addRequest(book);
        } else {
            System.out.println("Не удалось оставить запрос на книгу, книга " + bookId + " есть в наличии");
        }
    }

    public void exportBook(int bookId) {
        new CsvWriter().writeCsvFile("BooksTableForExport.csv", bookRepository.getBookById(bookId));
    }

    public void exportBooks() {
        new CsvWriter().writeCsvFile("BooksTableForExport.csv", bookRepository.getBooks());
    }

    public void importBooks() {
        List<Book> oldBooks = bookRepository.getBooks();
        int lastId = oldBooks.size() - 1;
        List<Book> newBooks = new CsvReader().readCsvFile("BooksTableForImport.csv", Book.class);

        for (Book book : newBooks) {
            if (book.getId() > lastId) {
                bookRepository.addBook(book);
            } else {
                bookRepository.updateBook(book);
            }
        }
    }

}
