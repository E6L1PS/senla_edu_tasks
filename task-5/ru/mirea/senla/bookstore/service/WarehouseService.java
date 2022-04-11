package ru.mirea.senla.bookstore.service;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.model.BookStatus;
import ru.mirea.senla.bookstore.repository.BookRepository;
import ru.mirea.senla.bookstore.repository.RequestRepository;
import ru.mirea.senla.bookstore.repository.WarehouseRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WarehouseService {

    private WarehouseRepository warehouseRepository;
    private BookRepository bookRepository;
    private RequestRepository requestRepository;

    public WarehouseService(WarehouseRepository warehouseRepository, BookRepository bookRepository, RequestRepository requestRepository) {
        this.warehouseRepository = warehouseRepository;
        this.bookRepository = bookRepository;
        this.requestRepository = requestRepository;
    }

    public WarehouseRepository getWarehouseRepository() {
        return warehouseRepository;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void addBook(int bookId) {
        Book bookById = bookRepository.getBookById(bookId);
        bookById.setStatus(BookStatus.IN_STOCK);
        bookById.setDeliveryDate(LocalDate.of(2001,1,1));
        warehouseRepository.addBook(bookById);
        System.out.println("Добавлена книга '" + bookId  + "' на склад, статус книги: " + bookRepository.getBookById(bookId).getStatus());
        requestRepository.clearRequests(bookById);
    }

    public void removeBook(int bookId) {
        Book bookById = bookRepository.getBookById(bookId);
        warehouseRepository.removeBook(bookById);

        if (warehouseRepository.getBooks().stream().anyMatch(x -> x.getId() == bookId)) {
            bookById.setStatus(BookStatus.IN_STOCK);
        } else {
            bookById.setStatus(BookStatus.OUT_STOCK);
        }

        System.out.println("Снята книга '" + bookId + "' со склада, статус книги: " + bookRepository.getBookById(bookId).getStatus());
    }

    public List<Book> getStaleBooks(Comparator comparator) {
        List<Book> staleBooks = new ArrayList<>();
        setStaleBooks(staleBooks);
        staleBooks.sort(comparator);
        return staleBooks;
    }

    public void setStaleBooks(List<Book> staleBooks) {
        for (Book book : warehouseRepository.getBooks()) {
            if (book.getDeliveryDate() != null) {
                if (LocalDate.now().minusMonths(6).isAfter(book.getDeliveryDate())) {
                    staleBooks.add(book);
                }
            }
        }
    }
}
