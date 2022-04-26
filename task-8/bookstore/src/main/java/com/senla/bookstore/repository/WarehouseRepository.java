package com.senla.bookstore.repository;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.repository.interfaces.IWarehouseRepository;
import com.senla.bookstore.util.json.JsonReader;
import com.senla.configure.annotations.Singleton;

import java.util.List;

@Singleton
public class WarehouseRepository implements IWarehouseRepository {

    private List<Book> books;

    public WarehouseRepository() {
        books = new JsonReader().readRepository("Warehouse.json", Book.class);
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

}
