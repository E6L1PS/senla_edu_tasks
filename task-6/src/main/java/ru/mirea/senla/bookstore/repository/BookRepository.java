package ru.mirea.senla.bookstore.repository;

import ru.mirea.senla.bookstore.model.Book;
import ru.mirea.senla.bookstore.repository.interfaces.IBookRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookRepository implements IBookRepository {

    private static int countBooksId = 0;
    private List<Book> books= new ArrayList<Book>();

    public BookRepository() {
        books.addAll(Arrays.asList(
                new Book("Штурм и буря", LocalDate.of(2010, Month.NOVEMBER, 22), 600, "Описание книги 'Штурм и буря'", countBooksId++),
                new Book("Портрет Дориана Грея", LocalDate.of(2011, Month.NOVEMBER, 22), 700, "Описание книги 'Портрет Дориана Грея'", countBooksId++),
                new Book("Годсгрейв", LocalDate.of(2012, Month.NOVEMBER, 23), 500, "Описание книги 'Годсгрейв'", countBooksId++),
                new Book("Крах и восход", LocalDate.of(2013, Month.NOVEMBER, 22), 1170, "Описание книги 'Крах и восход'", countBooksId++),
                new Book("Змей и голубка", LocalDate.of(2013, Month.NOVEMBER, 23), 800, "Описание книги 'Змей и голубка'", countBooksId++),
                new Book("1984", LocalDate.of(2015, Month.NOVEMBER, 22), 270, "Описание книги '1984'", countBooksId++)));
    }

    @Override
    public Book getBookById(int id) {
        return books.stream().filter(book -> book.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public void addBook(Book book) {
        book.setId(countBooksId++);
        books.add(book);
    }

    @Override
    public Book updateBook(Book book) {
        int id = book.getId();
        books.remove(id);
        books.add(id, book);
        return null;
    }

    @Override
    public void deleteBook(Book book) {
        books.remove(book);
    }

    @Override
    public void deleteBookById(int id) {
        books.remove(id);
    }

    @Override
    public int checkPrice(List<Integer> bookIds) {
        int sum = 0;

        for (Integer bookId : bookIds) {
            sum += books.get(bookId).getPrice();
        }

        return sum;
    }
}
