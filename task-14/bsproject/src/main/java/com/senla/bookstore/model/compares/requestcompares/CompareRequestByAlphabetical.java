package com.senla.bookstore.model.compares.requestcompares;

import com.senla.bookstore.model.Request;

import java.util.Comparator;

public class CompareRequestByAlphabetical implements Comparator<Request> {

    @Override
    public int compare(Request request1, Request request2) {
        return request1.getName().compareTo(request2.getName());
    }
}