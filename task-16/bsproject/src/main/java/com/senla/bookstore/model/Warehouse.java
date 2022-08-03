package com.senla.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "warehouse", schema = "public", catalog = "bookstore")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Warehouse implements Serializable, IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Book book;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    public Warehouse(Book book, LocalDate deliveryDate) {
        this.book = book;
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "[deliveryDate:" + deliveryDate +
                "]" + book +
                "}\n";
    }
}
