package com.senla.bookstore.util.csv;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class CsvReader {

    private static final String filePath = "bookstore\\src\\main\\csvtables\\";

    public <T> List<T> readCsvFile(String fileName, Class<T> t) {

        try (Reader reader = new FileReader(filePath + fileName)) {

            List<T> entities = (List<T>) new CsvToBeanBuilder<>(reader)
                    .withType(t)
                    .build()
                    .parse();

            for (T entity : entities) {
                System.out.println(entity);
            }

            return entities;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
