package com.senla.bookstore.model;

public class Customer implements IPerson {

    private static int countCustomerId = 0;
    private String name;
    private int customerId;

    public Customer() {
        customerId = countCustomerId++;
    }

    public Customer(int id, String name) {
        this.customerId = id;
    }
}
