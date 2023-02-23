package com.senla.bookstore.service;

import com.senla.bookstore.model.Author;
import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.BookStatus;
import com.senla.bookstore.model.Request;
import com.senla.bookstore.repository.interfaces.IAuthorRepository;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import com.senla.bookstore.repository.interfaces.IRequestRepository;
import com.senla.bookstore.service.interfaces.IBookService;
import com.senla.bookstore.util.csv.CsvReader;
import com.senla.bookstore.util.csv.CsvWriter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLClientInfoException;
import java.util.List;

@Slf4j
@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IAuthorRepository authorRepository;

    @Autowired
    private IRequestRepository requestRepository;

    @Transactional(readOnly = true)
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> getSortedBooks(String sortType) {
        return bookRepository.findAllByType(sortType);
    }

    @Transactional
    public String getDescription(Integer id) {
        return bookRepository.findDescriptionById(id);
    }

    public List<Request> getRequestBooks(String sortType) {
        return requestRepository.findAllByType(sortType);
    }

    @Transactional
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @SneakyThrows
    public void addRequest(Integer bookId) {
        Book book = bookRepository.findEntityById(bookId);
        if (book.getStatus() == BookStatus.OUT_STOCK) {
            requestRepository.create(book);
        } else {
            throw new SQLClientInfoException("Не удалось оставить запрос на книгу, книга " + bookId + " есть в наличии",null);
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