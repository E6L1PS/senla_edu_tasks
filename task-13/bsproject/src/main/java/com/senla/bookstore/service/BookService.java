package com.senla.bookstore.service;

import com.senla.bookstore.model.Author;
import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.BookStatus;
import com.senla.bookstore.model.Request;
import com.senla.bookstore.repository.interfaces.IAuthorRepository;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import com.senla.bookstore.repository.interfaces.IRequestRepository;
import com.senla.bookstore.service.interfaces.IBookService;
import com.senla.bookstore.model.compares.CompareStrategy;
import com.senla.bookstore.util.csv.CsvReader;
import com.senla.bookstore.util.csv.CsvWriter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@NoArgsConstructor
@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IAuthorRepository authorRepository;

    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private CompareStrategy compareStrategy;

    @Transactional
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getSortedBooks(String sortType) {
        List<Book> sortedBooks = new ArrayList<>(bookRepository.findAll());
        sortedBooks.sort(compareStrategy.getComparator(sortType));
        return sortedBooks;
    }

    @Transactional
    public String getDescription(Integer id) {
        return bookRepository.findEntityById(id).getDescription();
    }

    public List<Request> getRequestBooks(String sortType) {
        List<Request> sortedRequests = new ArrayList<>(requestRepository.findAll());
        sortedRequests.sort(compareStrategy.getComparator(sortType));
        return sortedRequests;
    }

    @Transactional
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public void addRequest(Integer bookId) {
        Book book = bookRepository.findEntityById(bookId);
        if (book.getStatus() == BookStatus.OUT_STOCK) {
            requestRepository.create(book);
        } else {
            System.out.println("Не удалось оставить запрос на книгу, книга " + bookId + " есть в наличии");
        }
    }

    public void exportBook(Integer bookId) {
        CsvWriter.writeCsvFile("BooksTableForExport.csv", bookRepository.findEntityById(bookId));
    }

    public void exportBooks() {
        CsvWriter.writeCsvFile("BooksTableForExport.csv", bookRepository.findAll());
    }

    public void importBooks() {
        List<Book> oldBooks = bookRepository.findAll();
        int lastId = oldBooks.size() - 1;
        List<Book> newBooks = CsvReader.readCsvFile("BooksTableForImport.csv", Book.class);

        for (Book book : newBooks) {
            if (book.getId() > lastId) {
                bookRepository.create(book);
            } else {
                bookRepository.update(book);
            }
        }
    }

}