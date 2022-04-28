package ru.mirea.senla.bookstore.repository;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.repository.interfaces.IBookRepository;

import java.util.ArrayList;
import java.util.List;

public class WarehouseRepository implements IBookRepository {

    private static WarehouseRepository instance = new WarehouseRepository();
    private List<Book> books = new ArrayList<>();

    private WarehouseRepository() {

    }

    @Override
    public Book getBookById(int id) {
        return books.stream().filter(book -> book.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void updateBook(Book book) {
    }

    @Override
    public void deleteBook(Book book) {
        books.remove(book);
    }

    @Override
    public void deleteBookById(int id) {
        books.remove(getBookById(id));
    }

    @Override
    public int checkPrice(List<Integer> bookIds) {
        int sum = 0;

        for (Integer bookId : bookIds) {
            sum += books.get(bookId).getPrice();
        }

        return sum;
    }

    public static WarehouseRepository getInstance() {
        return instance;
    }

}
