package ru.mirea.senla.bookstore.repository;

import ru.mirea.senla.bookstore.model.Book;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class BookRepository {
    private static int countBooksId = 0;

    private List<Book> books= Arrays.asList(
            new Book("Штурм и буря", LocalDate.of(2010, Month.NOVEMBER, 22), 600, "Описание Штурм и буря", countBooksId++),
            new Book("Портрет Дориана Грея", LocalDate.of(2011, Month.NOVEMBER, 22), 700, "Описание Портрет Дориана Грея", countBooksId++),
            new Book("Годсгрейв", LocalDate.of(2012, Month.NOVEMBER, 23), 500, "Описание Годсгрейв", countBooksId++),
            new Book("Крах и восход", LocalDate.of(2013, Month.NOVEMBER, 22), 1170, "Описание Крах и восход", countBooksId++),
            new Book("Змей и голубка", LocalDate.of(2013, Month.NOVEMBER, 23), 800, "Описание Змей и голубка", countBooksId++),
            new Book("1984", LocalDate.of(2015, Month.NOVEMBER, 22), 270, "Описание 1984", countBooksId++));

    public List<Book> getBooks() {
        return books;
    }

}
