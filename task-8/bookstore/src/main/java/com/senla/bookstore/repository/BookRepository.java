package com.senla.bookstore.repository;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import com.senla.bookstore.util.json.JsonReader;
import com.senla.configure.annotations.Singleton;

import java.util.List;

@Singleton
public class BookRepository implements IBookRepository {

    private static int countBooksId;
    private List<Book> books;

    public BookRepository() {
        books = new JsonReader().readRepository("Library.json", Book.class);
        countBooksId = books.size();
    }

    public int getCountBooksId() {
        return countBooksId;
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
        book.setId(countBooksId++);
        books.add(book);
    }

    @Override
    public void updateBook(Book book) {
        int id = book.getId();
        deleteBookById(id);
        books.add(id, book);
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
}
