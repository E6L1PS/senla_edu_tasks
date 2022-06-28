package com.senla.bookstore.model.compares.warehouscompares;

import com.senla.bookstore.model.Book;

import java.util.Comparator;

public class CompareWarehouseByDate implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        return book1.getDeliveryDate().compareTo(book2.getDeliveryDate());
    }
}
