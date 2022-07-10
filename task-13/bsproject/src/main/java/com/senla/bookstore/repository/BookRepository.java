package com.senla.bookstore.repository;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.List;

@Component
public class BookRepository extends AbstractRepository<Integer, Book> implements IBookRepository<Integer, Book> {

    public BookRepository() {
        setClazz(Book.class);
    }

    @Override
    public List<Book> findAll() {
        return super.findAll();
    }

    @Override
    public Book findEntityById(Integer id) {
        return super.findEntityById(id);
    }

    @Override
    public boolean create(Book entity) {
        return super.create(entity);
    }

    @Override
    public Book update(Book entity) {
        return super.update(entity);
    }

    @Override
    public boolean delete(Integer id) {
        return super.delete(id);
    }

    @Override
    public boolean delete(Book entity) {
        return super.delete(entity);
    }

    @Override
    public Integer checkPrice(List<Integer> bookIds) {
        int sum = 0;
        for (Integer bookId : bookIds) {
            sum += findPriceById(bookId);
        }

        return sum;
    }

    @Override
    public Integer findPriceById(Integer id) {
        TypedQuery<Integer> q = getEntityManager().createQuery("select price from Book b WHERE b.id=:id", Integer.class);
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }
}
