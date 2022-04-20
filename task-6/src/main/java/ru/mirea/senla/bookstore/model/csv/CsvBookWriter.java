package ru.mirea.senla.bookstore.model.csv;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import ru.mirea.senla.bookstore.model.Book;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CsvBookWriter implements CsvWritable<Book> {

    @Override
    public void writeCsvFile(Book book) {
        try {
            Writer writer = new FileWriter("BooksTableForExport.csv");

            StatefulBeanToCsv<Book> csvWriter = new StatefulBeanToCsvBuilder<Book>(writer)
                    .withSeparator(',')
                    .build();

            List<Book> books = new ArrayList<>();
            books.add(book);

            csvWriter.write(books);

            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void writeCsvFile(List<Book> books) {
        try {
            Writer writer = new FileWriter("BooksTableForExport.csv");

            StatefulBeanToCsv<Book> csvWriter = new StatefulBeanToCsvBuilder<Book>(writer)
                    .withSeparator(',')
                    .build();

            csvWriter.write(books);

            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
