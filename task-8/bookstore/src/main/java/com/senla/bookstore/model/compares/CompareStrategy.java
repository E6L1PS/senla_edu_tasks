package com.senla.bookstore.model.compares;

import com.senla.bookstore.model.compares.bookcompares.CompareBookByAlphabetical;
import com.senla.bookstore.model.compares.bookcompares.CompareBookByDate;
import com.senla.bookstore.model.compares.bookcompares.CompareBookByPrice;
import com.senla.bookstore.model.compares.bookcompares.CompareBookByStatus;
import com.senla.bookstore.model.compares.ordercompares.CompareOrderByDate;
import com.senla.bookstore.model.compares.ordercompares.CompareOrderByPrice;
import com.senla.bookstore.model.compares.ordercompares.CompareOrderByStatus;
import com.senla.bookstore.model.compares.requestcompares.CompareRequestByAlphabetical;
import com.senla.bookstore.model.compares.requestcompares.CompareRequestByNumber;
import com.senla.bookstore.model.compares.warehouscompares.CompareWarehouseByDate;
import com.senla.bookstore.model.compares.warehouscompares.CompareWarehouseByPrice;

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
