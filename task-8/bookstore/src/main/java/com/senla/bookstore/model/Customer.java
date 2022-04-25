package com.senla.bookstore.model;

public class Customer extends Person {

    private static int countCustomerId = 0;
    private int customerId;

    public Customer() {
        super();
        customerId = countCustomerId++;
    }

    public Customer(int id, String name, int age) {
        super(id, name, age);
        customerId = id;
    }

    public int getCustomerId() {
        return customerId;
    }
}
