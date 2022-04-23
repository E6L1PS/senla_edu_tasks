package ru.mirea.senla.bookstore.util.csv;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CsvWriter {

    private static final String filePath = "src\\main\\csvtables\\";

    public <T> void writeCsvFile(String fileName, T entity) {

        try(Writer writer = new FileWriter(filePath + fileName)) {
            StatefulBeanToCsv<T> csvWriter = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(',')
                    .build();

            List<T> entities = new ArrayList<>();
            entities.add(entity);
            csvWriter.write(entities);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public <T> void writeCsvFile(String fileName, List<T> entities) {

        try(Writer writer = new FileWriter(fileName)) {
            StatefulBeanToCsv<T> csvWriter = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(',')
                    .build();

            csvWriter.write(entities);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
