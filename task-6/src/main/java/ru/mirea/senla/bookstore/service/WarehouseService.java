package ru.mirea.senla.bookstore.service;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.model.BookStatus;
import ru.mirea.senla.bookstore.model.compares.CompareStrategy;
import ru.mirea.senla.bookstore.repository.interfaces.IBookRepository;
import ru.mirea.senla.bookstore.repository.interfaces.IRequestRepository;
import ru.mirea.senla.bookstore.service.interfaces.IWarehouseService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WarehouseService implements IWarehouseService {

    private IBookRepository warehouseRepository;
    private IBookRepository  bookRepository;
    private IRequestRepository requestRepository;

    public WarehouseService(IBookRepository  warehouseRepository, IBookRepository  bookRepository, IRequestRepository requestRepository) {
        this.warehouseRepository = warehouseRepository;
        this.bookRepository = bookRepository;
        this.requestRepository = requestRepository;
    }

    public IBookRepository getWarehouseRepository() {
        return warehouseRepository;
    }

    public IBookRepository getBookRepository() {
        return bookRepository;
    }

    public void addBook(int bookId) {
        Book bookById = bookRepository.getBookById(bookId);
        bookById.setStatus(BookStatus.IN_STOCK);
        bookById.setDeliveryDate(LocalDate.of(2001,1,1));
        warehouseRepository.addBook(bookById);
        System.out.println("Добавлена книга '" + bookId  + "' на склад, статус книги: " + bookRepository.getBookById(bookId).getStatus());
        requestRepository.deleteRequests(bookById);
    }

    public void removeBook(int bookId) {
        Book bookById = bookRepository.getBookById(bookId);
        warehouseRepository.deleteBook(bookById);

        if (warehouseRepository.getBooks().stream().anyMatch(x -> x.getId() == bookId)) {
            bookById.setStatus(BookStatus.IN_STOCK);
        } else {
            bookById.setStatus(BookStatus.OUT_STOCK);
        }

        System.out.println("Снята книга '" + bookId + "' со склада, статус книги: " + bookRepository.getBookById(bookId).getStatus());
    }

    public List<Book> getStaleBooks(String key) {
        List<Book> staleBooks = new ArrayList<>();
        setStaleBooks(staleBooks);
        staleBooks.sort(new CompareStrategy().getComparator(key));
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
