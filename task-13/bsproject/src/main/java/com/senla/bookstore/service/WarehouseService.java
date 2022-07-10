package com.senla.bookstore.service;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.BookStatus;
import com.senla.bookstore.model.Warehouse;
import com.senla.bookstore.repository.CustomerRepository;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import com.senla.bookstore.repository.interfaces.IRequestRepository;
import com.senla.bookstore.repository.interfaces.IWarehouseRepository;
import com.senla.bookstore.service.interfaces.IWarehouseService;
import com.senla.bookstore.model.compares.CompareStrategy;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@NoArgsConstructor
@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    private IWarehouseRepository warehouseRepository;

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    @Autowired
    private CompareStrategy compareStrategy;

    @Value("${deletingRequests}")
    private String deletingRequests;

    @Value("${numberMonthForStale}")
    private String numberMonthForStale;

    public IWarehouseRepository getWarehouseRepository() {
        return warehouseRepository;
    }

    public IBookRepository getBookRepository() {
        return bookRepository;
    }

    @Transactional
    public void addBook(Integer bookId) {
        Book bookById = bookRepository.findEntityById(bookId);

        //TODO set status in book table

        bookById.setStatus(BookStatus.IN_STOCK);
        warehouseRepository.create(new Warehouse(bookById, LocalDate.now()));
        System.out.println("Добавлена книга '" + bookId + "' на склад, статус книги: " + bookById.getStatus());

        if (Boolean.parseBoolean(deletingRequests)) {
            //TODO  requestRepository.deleteRequestsByBook(bookById);
        }

    }

    @Transactional
    public void removeBook(Integer bookId) {
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

    @Override
    public List<Book> getStaleBooks(String key) {
        return null;
    }

//
//    public List<Book> getStaleBooks(String sortType) {
//        List<Book> staleBooks = new ArrayList<>();
//        setStaleBooks(staleBooks);
//        staleBooks.sort(compareStrategy.getComparator(sortType));
//        return staleBooks;
//    }
//
//    public void setStaleBooks(List<Book> staleBooks) {
//        //  int numberMonthForStale = Integer.parseInt(new PropertyUtil().getPropertyValue("NUMBER_MONTHS_FOR_STALE"));
//        List<Book> books = warehouseRepository.findAll();
//        for (Book book : books) {
//            if (book.getDeliveryDate() != null) {
//                if (LocalDate.now().minusMonths(Integer.parseInt(numberMonthForStale)).isAfter(book.getDeliveryDate())) {
//                    staleBooks.add(book);
//                }
//            }
//        }
//    }

    public String getNumberMonthForStale() {
        return numberMonthForStale;
    }

    public void setNumberMonthForStale(String numberMonthForStale) {
        this.numberMonthForStale = numberMonthForStale;
    }


}
