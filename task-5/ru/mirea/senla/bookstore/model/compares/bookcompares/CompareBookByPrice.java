package ru.mirea.senla.bookstore.model.compares.bookcompares;

import ru.mirea.senla.bookstore.model.Book;

import java.util.Comparator;

public class CompareBookByPrice implements Comparator<Book> {

    @Override
    public int compare(Book book1, Book book2) {
        return book1.getPrice() - book2.getPrice();
    }
}
