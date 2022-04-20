package ru.mirea.senla.bookstore.repository;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.repository.interfaces.IBookRepository;

import java.util.ArrayList;
import java.util.List;

public class WarehouseRepository implements IBookRepository {

    private List<Book> books = new ArrayList<>();

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
    public Book updateBook(Book book) {
        return null;
    }

    @Override
    public void deleteBook(Book book) {
        books.remove(book);
    }

    @Override
    public void deleteBookById(int id) {
        books.remove(id);
    }

}
