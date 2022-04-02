package ru.mirea.senla.bookstore.service;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.model.BookStatus;
import ru.mirea.senla.bookstore.repository.BookRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private BookRepository bookRepository;
    private List<Book> sortedBooks;
    private List<Book> sortedRequestBooks;
    private List<Book> staleBooks = new ArrayList<>();

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.sortedBooks = new ArrayList<>(bookRepository.getBooks());
        this.sortedRequestBooks = new ArrayList<>(bookRepository.getBooks());
    }

    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }

    public void addRequest(int bookId) {
        if (bookRepository.getBookById(bookId).getStatus() == BookStatus.OUT_STOCK) {
            bookRepository.getBookById(bookId).addRequest();
        } else {
            System.out.println("Не удалось оставить запрос на книгу, книга " + bookId + " есть в наличии");
        }
    }

    public String showBooks() {
        return sortedBooks.toString().replaceAll(", ", "")
                .replace("[", "")
                .replace("]", "");
    }

    public String showStaleBooks() {
        return staleBooks.toString().replaceAll(", ", "")
                .replace("[", "")
                .replace("]", "");
    }

    public void setStaleBooks() {
        for (Book book : sortedBooks) {
            if (book.getDeliveryDate() != null) {
                if (LocalDate.now().minusMonths(6).isAfter(book.getDeliveryDate())) {
                    staleBooks.add(book);
                }
            }
        }
    }

    public String showRequestBooks() {
        return sortedRequestBooks.toString();
    }

    public String getDescription(int id) {
        return bookRepository.getBooks().get(id).getDescription();
    }

    //Сортировка книг
    public void sortByAlphabetical() {
        this.sortedBooks.sort((book1, book2) -> book1.getName().compareTo(book2.getName()));
    }

    public void sortByPrice() {
        this.sortedBooks.sort((book1, book2) -> book1.getPrice() - book2.getPrice());
    }

    public void sortByDate() {
        this.sortedBooks.sort((book1, book2) -> book1.getPublicationDate().compareTo(book2.getPublicationDate()));
    }

    public void sortByStatus() {
        this.sortedBooks.sort((book1, book2) -> book1.getStatus().compareTo(book2.getStatus()));
    }

    //Сортировка запросов
    public void sortByRequestNumber() {
        this.sortedBooks.sort((book1, book2) -> book1.getRequestNumber() - book2.getRequestNumber());
    }

    //Сортировка залежавших книг
    public void sortStaleBooksByPrice() {
        this.staleBooks.sort((book1, book2) -> book1.getPrice() - book2.getPrice());
    }

    public void sortStaleBooksByDate() {
        this.staleBooks.sort((book1, book2) -> book1.getPublicationDate().compareTo(book2.getPublicationDate()));
    }
}
