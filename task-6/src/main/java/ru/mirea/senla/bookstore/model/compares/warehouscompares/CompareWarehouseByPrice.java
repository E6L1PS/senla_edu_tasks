package ru.mirea.senla.bookstore.model.compares.warehouscompares;

import ru.mirea.senla.bookstore.model.Book;

import java.util.Comparator;

public class CompareWarehouseByPrice implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        return book1.getPrice() - book2.getPrice();
    }
}
