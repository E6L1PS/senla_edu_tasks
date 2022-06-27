package com.senla.bookstore.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.opencsv.bean.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Book implements IEntity, Serializable {

    @CsvBindByPosition(position = 0)
    private int id;

    @CsvBindByPosition(position = 2)
    private int price;

    private String isbn;

    @CsvBindByPosition(position = 1)
    private String name;

    @CsvBindByPosition(position = 6)
    private String description;

    @CsvBindByPosition(position = 3)
    private BookStatus status;

    @CsvIgnore
    private Request request;


    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @CsvBindByPosition(position = 4)
    @CsvDate(value = "yyyy-MM-dd")
    private LocalDate publicationDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @CsvBindByPosition(position = 5)
    @CsvDate(value = "yyyy-MM-dd")
    private LocalDate deliveryDate;

    public Book() {
        this.status = BookStatus.OUT_STOCK;
        this.request = new Request(id, name, 0);
    }

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

        this.request = new Request(id, this.name, 0);
        this.status = BookStatus.OUT_STOCK;
    }

    public Book(String name, int price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.status = BookStatus.OUT_STOCK;
        this.request = new Request(id, this.name, 0);
    }

    public Book(String name, LocalDate publicationDate, int price, String description, int id) {
        this.name = name;
        this.publicationDate = publicationDate;
        this.price = price;
        this.description = description;
        this.status = BookStatus.OUT_STOCK;
        this.id = id;
        this.request = new Request(id, name, 0);
        //this.deliveryDate = deliveryDate;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BookStatus getStatus() {
        return status;
    }

    public Request getRequest() {
        request.setName(name);
        request.setId(id);
        return request;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
