package com.senla.bookstore.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders", schema = "public", catalog = "bookstore")
@AllArgsConstructor
@Getter
@Setter
public class Order implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer price;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders_books",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    @Enumerated(EnumType.STRING)
    @Type(type = "com.senla.bookstore.util.EnumTypePostgreSql")
    private OrderStatus status;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "issue_date")
    private LocalDate issueDate = LocalDate.now();

    public Order() {
        this.status = OrderStatus.NEW;
    }

    public Order(Customer customer, List<Book> books, Integer price) {
        this.customer = customer;
        this.books = books;
        this.price = price;
        this.status = OrderStatus.NEW;
    }

    public Order(Integer id, Integer price, List<Book> books, OrderStatus status, LocalDate issueDate) {
        this.id = id;
        this.price = price;
        this.books = books;
        this.status = status;
        this.issueDate = issueDate;
    }

    public List<Integer> getBookIds() {
        List<Integer> bookIds = new ArrayList<>();
        for (Book book : getBooks()) {
            bookIds.add(book.getId());
        }
        return bookIds;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", customer=" + customer +
                ", employee=" + employee +
                ", books=" + books +
                ", status=" + status +
                ", issueDate=" + issueDate +
                "}\n";
    }
}
