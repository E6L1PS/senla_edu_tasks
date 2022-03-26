package ru.mirea.senla.bookstore.repository;

import ru.mirea.senla.bookstore.model.Book;

import java.util.ArrayList;
import java.util.List;

public class WarehouseRepository {
    private List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public Book getBook(int id) {
        return books.stream().filter(book -> book.getId() == id).findAny().orElse(null);
    }
}
