package com.senla.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.bookstore.model.Author;
import com.senla.bookstore.model.BookStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookDTO {

    private Integer id;
    private Integer price;
    private String name;
    private String isbn;
    private String description;
    private BookStatus status;
    private List<Author> authors;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;
}
