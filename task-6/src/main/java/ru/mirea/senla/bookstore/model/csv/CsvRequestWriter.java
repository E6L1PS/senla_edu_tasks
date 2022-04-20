package ru.mirea.senla.bookstore.model.csv;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import ru.mirea.senla.bookstore.model.Request;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CsvRequestWriter implements CsvWritable<Request> {

    @Override
    public void writeCsvFile(Request request) {
        try {
            Writer writer = new FileWriter("RequestsTable.csv");

            StatefulBeanToCsv<Request> csvWriter = new StatefulBeanToCsvBuilder<Request>(writer)
                    .withSeparator(',')
                    .build();


            List<Request> requests = new ArrayList<>();
            requests.add(request);

            csvWriter.write(requests);

            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void writeCsvFile(List<Request> requests) {
        try {
            Writer writer = new FileWriter("RequestsTable.csv");

            StatefulBeanToCsv<Request> csvWriter = new StatefulBeanToCsvBuilder<Request>(writer)
                    .withSeparator(',')
                    .build();

            csvWriter.write(requests);

            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
