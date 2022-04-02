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
    private int[] bookIds;
    private OrderStatus status;

    public Order(Customer customer, int[] booksId, int price) {
        this.customer = customer;
        this.bookIds = booksId;
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

    public int[] getBookIds() {
        return bookIds;
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


    public void setBookIds(int[] bookIds) {
        this.bookIds = bookIds;
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
                ", books=" + Arrays.toString(bookIds) +
                ", status=" + status +
                '}' + '\n';
    }
}
