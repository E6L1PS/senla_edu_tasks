package com.senla.bookstore.util.csv;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CsvWriter {

    private static final String filePath = "bookstore\\src\\main\\csvtables\\";

    public static <T> void writeCsvFile(String fileName, T entity) {
        List<T> entities = new ArrayList<>();
        entities.add(entity);
        writeCsvFile(fileName, entities);
    }

    public static <T> void writeCsvFile(String fileName, List<T> entities) {

        try (Writer writer = new FileWriter(filePath + fileName)) {
            StatefulBeanToCsv<T> csvWriter = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(',')
                    .build();

            csvWriter.write(entities);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
