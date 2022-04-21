package ru.mirea.senla.bookstore.model.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import ru.mirea.senla.bookstore.model.Book;

import java.io.*;
import java.util.List;

public class CsvBookReader implements CsvReadable<Book> {

    public List<Book> readCsvFile() {
        try {
            Reader reader = new FileReader("BooksTableForInput.csv");

            List<Book> books = new CsvToBeanBuilder<Book>(reader)
                    .withType(Book.class)
                    .build()
                    .parse();

            for (Book book : books) {
                System.out.println(book);
            }
            reader.close();
            return books;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
