package ru.mirea.senla.bookstore.model;

public class Employee extends Person {
    private int employeeId;

    public Employee(int id, String name, int age) {
        super(id, name, age);
    }
}
