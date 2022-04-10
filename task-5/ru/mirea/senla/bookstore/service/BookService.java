package ru.mirea.senla.bookstore.service;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.model.BookStatus;
import ru.mirea.senla.bookstore.model.Request;
import ru.mirea.senla.bookstore.repository.BookRepository;
import ru.mirea.senla.bookstore.repository.RequestRepository;

import java.util.*;

public class BookService {

    private BookRepository bookRepository;
    private RequestRepository requestRepository;

    public BookService(BookRepository bookRepository, RequestRepository requestRepository) {
        this.bookRepository = bookRepository;
        this.requestRepository = requestRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }


    public void addRequest(int bookId) {
        Book book = bookRepository.getBookById(bookId);
        if (book.getStatus() == BookStatus.OUT_STOCK) {
            requestRepository.addRequest(book);
        } else {
            System.out.println("Не удалось оставить запрос на книгу, книга " + bookId + " есть в наличии");
        }
    }

    public List<Book> getSortedBooks(Comparator comparator) {
        List<Book> sortedBooks = new ArrayList<>(bookRepository.getBooks());
        sortedBooks.sort(comparator);
        return sortedBooks;
    }

    public String getDescription(int id) {
        return bookRepository.getBookById(id).getDescription();
    }

    public List<Request> getRequestBooks(Comparator comparator) {
        List<Request> sortedRequests = new ArrayList<>(requestRepository.getRequests());
        sortedRequests.sort(comparator);
        return sortedRequests;
    }
}
