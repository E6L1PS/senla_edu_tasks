package ru.mirea.senla.task3.task3_4;

public class Order {
    private int id;
    private int employeeId;
    private int customerId;
    private int[] books;
    private String status;

    public Order(int id, int customerId, int[] books) {
        this.id = id;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.books = books;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int[] getBooks() {
        return books;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setBooks(int[] books) {
        this.books = books;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
