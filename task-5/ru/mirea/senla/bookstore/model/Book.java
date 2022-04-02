package ru.mirea.senla.bookstore.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private String name;
    private LocalDate publicationDate;
    private int price;
    private String description;
    private BookStatus status;
    private int id;
    private int requestNumber = 0;
   // private List<Integer> request = new ArrayList<>();
    private LocalDate deliveryDate;

    public Book(String name, LocalDate publicationDate, int price, String description, int id) {
        this.name = name;
        this.publicationDate = publicationDate;
        this.price = price;
        this.description = description;
        this.status = BookStatus.OUT_STOCK;
        this.id = id;
//        this.request = request;
//        this.deliveryDate = deliveryDate;
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public void addRequest() {
        requestNumber++;
    }

    public void clearRequest() {
        requestNumber = 0;
    }

    public String getName() {
        return name;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public BookStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    //public List<Integer> getRequest() {return request;}

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    //public void setRequest(List<Integer> request) {this.request = request;}

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id = " + id +
                ",    publicationDate = " + publicationDate +
                ",    status = " + status +
                ",    requestNumber = " + requestNumber +
                ",    price = " + price +
                ",    deliveryDate = " + deliveryDate +
                ",    name = '" + name + '\'' +
                ",    description ='" + description + '\'' +
                "" +
                "}\n";
    }
}
