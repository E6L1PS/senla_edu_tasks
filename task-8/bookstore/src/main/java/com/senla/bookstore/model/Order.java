package com.senla.bookstore.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.opencsv.bean.CsvBindByPosition;

import java.time.LocalDate;
import java.util.List;

public class Order implements IEntity {

    @CsvBindByPosition(position = 0)
    private int id;

    @CsvBindByPosition(position = 1)
    private int price;

    @CsvBindByPosition(position = 2)
    private Customer customer;
    private Employee employee;
    private List<Integer> bookIds;

    @CsvBindByPosition(position = 3)
    private OrderStatus status;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate issueDate = LocalDate.now();

    public Order() {
        this.status = OrderStatus.NEW;
    }

    public Order(Customer customer, List<Integer> booksId, int price) {
        this.customer = customer;
        this.bookIds = booksId;
        this.price = price;
        this.status = OrderStatus.NEW;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getBookIds() {
        return bookIds;
    }

    public OrderStatus getStatus() {
        return status;
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


    public void setBookIds(List<Integer> bookIds) {
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
                ", books=" + bookIds +
                ", status=" + status +
                '}' + '\n';
    }
}
