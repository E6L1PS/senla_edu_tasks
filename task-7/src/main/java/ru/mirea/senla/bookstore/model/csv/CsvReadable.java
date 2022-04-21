package ru.mirea.senla.bookstore.model.csv;

import java.util.List;

public interface CsvReadable<T> {

    List<T> readCsvFile();
}
