package com.senla.bookstore.service;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.BookStatus;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import com.senla.bookstore.repository.interfaces.IRequestRepository;
import com.senla.bookstore.repository.interfaces.IWarehouseRepository;
import com.senla.bookstore.service.interfaces.IWarehouseService;
import com.senla.bookstore.model.compares.CompareStrategy;
import com.senla.configure.annotations.Autowired;
import com.senla.configure.annotations.ConfigProperty;
import com.senla.configure.annotations.Singleton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class WarehouseService implements IWarehouseService {

    @Autowired
    private IWarehouseRepository warehouseRepository;

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private CompareStrategy compareStrategy;

    @ConfigProperty
    private String deletingRequests;

    @ConfigProperty
    private String numberMonthForStale;

    public WarehouseService() {

    }

    public IWarehouseRepository getWarehouseRepository() {
        return warehouseRepository;
    }

    public IBookRepository getBookRepository() {
        return bookRepository;
    }

    public void addBook(int bookId) {
        Book bookById = bookRepository.findEntityById(bookId);

        bookById.setStatus(BookStatus.IN_STOCK);
        bookById.setDeliveryDate(LocalDate.now());
        warehouseRepository.create(bookById);
        System.out.println("Добавлена книга '" + bookId + "' на склад, статус книги: " + bookById.getStatus());

        if (Boolean.parseBoolean(deletingRequests)) {
            //TODO  requestRepository.deleteRequestsByBook(bookById);
        }

    }

    public void removeBook(int bookId) {
        List<Book> books = warehouseRepository.findAll();
        Book bookById = bookRepository.findEntityById(bookId);
        warehouseRepository.delete(bookId);

        if (books.stream().anyMatch(x -> x.getId() == bookId)) {
            bookById.setStatus(BookStatus.IN_STOCK);
        } else {
            bookById.setStatus(BookStatus.OUT_STOCK);
        }
        bookRepository.update(bookById);
        System.out.println("Снята книга '" + bookId + "' со склада, статус книги: " + bookRepository.findEntityById(bookId).getStatus());
    }

    public List<Book> getStaleBooks(String sortType) {
        List<Book> staleBooks = new ArrayList<>();
        setStaleBooks(staleBooks);
        staleBooks.sort(compareStrategy.getComparator(sortType));
        return staleBooks;
    }

    public void setStaleBooks(List<Book> staleBooks) {
        //  int numberMonthForStale = Integer.parseInt(new PropertyUtil().getPropertyValue("NUMBER_MONTHS_FOR_STALE"));
        List<Book> books = warehouseRepository.findAll();
        for (Book book : books) {
            if (book.getDeliveryDate() != null) {
                if (LocalDate.now().minusMonths(Integer.parseInt(numberMonthForStale)).isAfter(book.getDeliveryDate())) {
                    staleBooks.add(book);
                }
            }
        }
    }

    public String getNumberMonthForStale() {
        return numberMonthForStale;
    }

    public void setNumberMonthForStale(String numberMonthForStale) {
        this.numberMonthForStale = numberMonthForStale;
    }
}
