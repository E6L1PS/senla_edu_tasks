package com.senla.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

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
import javax.persistence.Table;
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
    @Type(type = "com.senla.bookstore.util.EnumTypePostgreSql")
    private BookStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @CsvDate(value = "yyyy-MM-dd")
    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @JsonIgnore()
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "orders_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orders;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    public Book() {
        this.status = BookStatus.OUT_STOCK;
    }

    public Book(int id, int price, String isbn, String name, String description, BookStatus status, LocalDate publicationDate, LocalDate deliveryDate) {
        this.id = id;
        this.price = price;
        this.isbn = isbn;
        this.name = name;
        this.description = description;
        this.status = status;
        this.publicationDate = publicationDate;
    }

    public Book(int id, int price, String name, String description, LocalDate publicationDate, LocalDate deliveryDate) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.publicationDate = publicationDate;
        this.status = BookStatus.OUT_STOCK;
    }

    public Book(String name, int price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.status = BookStatus.OUT_STOCK;
    }

    public Book(String name, LocalDate publicationDate, int price, String description, int id) {
        this.name = name;
        this.publicationDate = publicationDate;
        this.price = price;
        this.description = description;
        this.status = BookStatus.OUT_STOCK;
        this.id = id;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", price=" + price +
                ", isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", publicationDate=" + publicationDate +
                ", authors=" + authors +
                "}\n";
    }
}