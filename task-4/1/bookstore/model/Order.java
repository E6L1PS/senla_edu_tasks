package ru.mirea.senla.bookstore.model;

import java.time.LocalDate;
import java.util.Arrays;

public class Order {
    private static int countOrderId = 0;
    private LocalDate issueDate = LocalDate.now();
    private int price;
    private int id;
    private Customer customer;
    private Employee employee;
    private int[] booksId;
    private OrderStatus status;

    public Order(Customer customer, int[] booksId, int price) {
        this.customer = customer;
        this.booksId = booksId;
        this.id = countOrderId++;
        this.price = price;
        this.status = OrderStatus.NEW;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public int getId() {
        return id;
    }

    public int[] getBooksId() {
        return booksId;
    }

    public String getStatus() {
        return status.getNameStatus();
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setBooksId(int[] booksId) {
        this.booksId = booksId;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "issueDate=" + issueDate +
                ", price=" + price +
                ", id=" + id +
                ", customer=" + customer +
                ", books=" + Arrays.toString(booksId) +
                ", status=" + status +
                '}' + '\n';
    }
}
