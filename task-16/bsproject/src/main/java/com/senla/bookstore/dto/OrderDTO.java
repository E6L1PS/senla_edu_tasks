package com.senla.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.OrderStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {

    private Integer id;
    private Integer price;
    private OrderStatus status;
    private List<Book> books;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate issueDate;
}
