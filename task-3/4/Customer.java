package ru.mirea.senla.task3.task3_4;

public class Customer extends Person {
    private int customerId;

    public Customer(int id, String name, int age) {
        super(id, name, age);
        customerId = id;
    }

    public int getCustomerId() {
        return customerId;
    }
}
