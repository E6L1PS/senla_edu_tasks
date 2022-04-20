package ru.mirea.senla.bookstore.model.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.model.Order;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CsvOrderReader implements CsvReadable<Order>{

    @Override
    public List<Order> readCsvFile() {
        try {
            Reader reader = new FileReader("OrdersTableForInput.csv");

            List<Order> orders = new CsvToBeanBuilder<Order>(reader)
                    .withType(Order.class)
                    .build()
                    .parse();

            for (Order order : orders) {
                System.out.println(order);
            }
            reader.close();
            return orders;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
