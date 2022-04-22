package ru.mirea.senla.bookstore.model.csv;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import ru.mirea.senla.bookstore.model.Order;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CsvOrderWriter implements CsvWritable<Order>{

    @Override
    public void writeCsvFile(Order order) {
        try {
            Writer writer = new FileWriter("OrdersTableForExport.csv");

            StatefulBeanToCsv<Order> csvWriter = new StatefulBeanToCsvBuilder<Order>(writer)
                    .withSeparator(',')
                    .build();


            List<Order> orders = new ArrayList<>();
            orders.add(order);

            csvWriter.write(orders);

            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void writeCsvFile(List<Order> orders) {
        try {
            Writer writer = new FileWriter("OrdersTableForExport.csv");

            StatefulBeanToCsv<Order> csvWriter = new StatefulBeanToCsvBuilder<Order>(writer)
                    .withSeparator(',')
                    .build();

            csvWriter.write(orders);

            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
