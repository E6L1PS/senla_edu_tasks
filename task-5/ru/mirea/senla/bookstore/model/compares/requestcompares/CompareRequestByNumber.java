package ru.mirea.senla.bookstore.model.compares.requestcompares;

import ru.mirea.senla.bookstore.model.Request;

import java.util.Comparator;

public class CompareRequestByNumber implements Comparator<Request> {

    @Override
    public int compare(Request request1, Request request2) {
        return request1.getNumber() - request2.getNumber();
    }
}
