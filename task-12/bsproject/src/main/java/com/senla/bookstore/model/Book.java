package com.senla.bookstore.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.opencsv.bean.*;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books", schema = "public", catalog = "bookstore")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book implements IEntity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer price;

    private String isbn;

    private String name;

    private String description;
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @OneToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    @CsvIgnore
    private Request request;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @CsvDate(value = "yyyy-MM-dd")
    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @CsvDate(value = "yyyy-MM-dd")
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "orders_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orders;

    public Book() {
        this.status = BookStatus.OUT_STOCK;
        this.request = new Request(id, 0, name);
    }

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    public Book(int id, int price, String isbn, String name, String description, BookStatus status, LocalDate publicationDate, LocalDate deliveryDate) {
        this.id = id;
        this.price = price;
        this.isbn = isbn;
        this.name = name;
        this.description = description;
        this.status = status;
        this.request = request;
        this.publicationDate = publicationDate;
        this.deliveryDate = deliveryDate;
    }

    public Book(int id, int price, String name, String description, LocalDate publicationDate, LocalDate deliveryDate) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.publicationDate = publicationDate;
        this.deliveryDate = deliveryDate;

        this.request = new Request(id, 0, this.name);
        this.status = BookStatus.OUT_STOCK;
    }

    public Book(String name, int price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.status = BookStatus.OUT_STOCK;
        this.request = new Request(id, 0, this.name);
    }

    public Book(String name, LocalDate publicationDate, int price, String description, int id) {
        this.name = name;
        this.publicationDate = publicationDate;
        this.price = price;
        this.description = description;
        this.status = BookStatus.OUT_STOCK;
        this.id = id;
        this.request = new Request(id, 0, this.name);
        //this.deliveryDate = deliveryDate;
    }

    public Request getRequest() {
        request.setName(name);
        request.setId(id);
        return request;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id = " + id +
                ",    publicationDate = " + publicationDate +
                ",    status = " + status +
                ",    requestNumber = " + //request.getNumber() +
                ",    price = " + price +
                ",    deliveryDate = " + deliveryDate +
                ",    name = '" + name + '\'' +
                ",    description ='" + description + '\'' +
                "" +
                "}\n";
    }
}
