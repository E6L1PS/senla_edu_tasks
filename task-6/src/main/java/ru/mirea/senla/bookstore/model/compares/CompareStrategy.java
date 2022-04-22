package ru.mirea.senla.bookstore.model.compares;

import ru.mirea.senla.bookstore.model.compares.bookcompares.CompareBookByAlphabetical;
import ru.mirea.senla.bookstore.model.compares.bookcompares.CompareBookByDate;
import ru.mirea.senla.bookstore.model.compares.bookcompares.CompareBookByPrice;
import ru.mirea.senla.bookstore.model.compares.bookcompares.CompareBookByStatus;
import ru.mirea.senla.bookstore.model.compares.ordercompares.CompareOrderByDate;
import ru.mirea.senla.bookstore.model.compares.ordercompares.CompareOrderByPrice;
import ru.mirea.senla.bookstore.model.compares.ordercompares.CompareOrderByStatus;
import ru.mirea.senla.bookstore.model.compares.requestcompares.CompareRequestByAlphabetical;
import ru.mirea.senla.bookstore.model.compares.requestcompares.CompareRequestByNumber;
import ru.mirea.senla.bookstore.model.compares.warehouscompares.CompareWarehouseByDate;
import ru.mirea.senla.bookstore.model.compares.warehouscompares.CompareWarehouseByPrice;

import java.util.*;

public class CompareStrategy {

    Map<String, Comparator> comparatorMap = new HashMap<>();

    public CompareStrategy() {
        this.comparatorMap.put("BookByAlphabetical", new CompareBookByAlphabetical());
        this.comparatorMap.put("BookByDate", new CompareBookByDate());
        this.comparatorMap.put("BookByPrice", new CompareBookByPrice());
        this.comparatorMap.put("BookByStatus", new CompareBookByStatus());

        this.comparatorMap.put("OrderByDate", new CompareOrderByDate());
        this.comparatorMap.put("OrderByPrice", new CompareOrderByPrice());
        this.comparatorMap.put("OrderByStatus", new CompareOrderByStatus());

        this.comparatorMap.put("RequestByAlphabetical", new CompareRequestByAlphabetical());
        this.comparatorMap.put("RequestByNumber", new CompareRequestByNumber());


        this.comparatorMap.put("WarehouseByDate", new CompareWarehouseByDate());
        this.comparatorMap.put("WarehouseByPrice", new CompareWarehouseByPrice());
    }

    public Comparator getComparator(String key) {
        return comparatorMap.get(key);
    }
}
