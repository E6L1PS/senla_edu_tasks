package com.senla.bookstore.model.compares.bookcompares;

import com.senla.bookstore.model.Book;

import java.util.Comparator;

public class CompareBookByAlphabetical implements Comparator<Book> {

    @Override
    public int compare(Book book1, Book book2) {
        return book1.getName().compareTo(book2.getName());
    }
}
