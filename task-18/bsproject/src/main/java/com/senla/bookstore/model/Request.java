package com.senla.bookstore.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "requests", schema = "public", catalog = "bookstore")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Request implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Book book;

    public Request(Book book, String name) {
        this.book = book;
        this.name = name;
    }

    public Request(Integer id, Integer number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }

    public void addRequest() {
        this.number++;
    }

    public void removeRequest() {
        this.number--;
    }

    public void clearRequests() {
        this.number = 0;
    }

}
