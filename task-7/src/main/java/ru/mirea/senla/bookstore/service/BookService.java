package ru.mirea.senla.bookstore.service;

import ru.mirea.senla.bookstore.model.*;
import ru.mirea.senla.bookstore.model.compares.CompareStrategy;
import ru.mirea.senla.bookstore.util.csv.CsvReader;
import ru.mirea.senla.bookstore.util.csv.CsvWriter;
import ru.mirea.senla.bookstore.repository.interfaces.IBookRepository;
import ru.mirea.senla.bookstore.repository.interfaces.IRequestRepository;
import ru.mirea.senla.bookstore.service.interfaces.IBookService;

import java.util.ArrayList;
import java.util.List;


public class BookService implements IBookService {

    private IBookRepository bookRepository;
    private IRequestRepository requestRepository;

    public BookService(IBookRepository bookRepository, IRequestRepository requestRepository) {
        this.bookRepository = bookRepository;
        this.requestRepository = requestRepository;
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
