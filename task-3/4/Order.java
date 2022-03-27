package ru.mirea.senla.task3.task3_4;

public class Order {
    private int id;
    private Customer customer;
    private Employee employee;
    private int[] books;
    private OrderStatus status;

    public Order(int id, Customer customer, int[] books) {
        this.id = id;
        this.customer = customer;
        this.books = books;
        this.status = OrderStatus.NEW;
    }

    public int getId() {
        return id;
    }

    public int[] getBooks() {
        return books;
    }

    public String getStatus() {
        return status.getNameStatus();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setBooks(int[] books) {
        this.books = books;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
