package ru.mirea.senla.bookstore.service;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.model.BookStatus;
import ru.mirea.senla.bookstore.model.Order;
import ru.mirea.senla.bookstore.repository.BookRepository;

import java.util.List;
//@Service
public class BookService {
    //@Autowired
    private BookRepository bookRepository;
    private List<Book> sortedBooks;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.sortedBooks = bookRepository.getBooks();
    }

    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }

    public void addRequest(int bookId, Order order) {
        if (bookRepository.getBooks().get(bookId).getStatus() == BookStatus.OUT_STOCK) {
            bookRepository.getBooks().get(bookId).getRequest().add(order.getId());
        } else System.out.println("Не удалось оставить запрос на книгу, книга " + bookId + " есть в наличии");
    }

    public String showBooks() {
        return sortedBooks.toString();
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
}
