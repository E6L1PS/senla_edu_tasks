package ru.mirea.senla.bookstore.model.csv;

import java.util.List;

public interface CsvWritable<T> {

    void writeCsvFile(T t);

    void writeCsvFile(List<T> t);
}
