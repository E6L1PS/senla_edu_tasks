package com.senla.bookstore.repository;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.sql.*;
import java.util.List;

@Component
public class BookRepository extends AbstractRepository<Integer, Book> implements IBookRepository<Integer, Book> {

    public static final String SQL_SELECT_CHECK_PRICE_BY_ID = "SELECT price FROM books WHERE id= :paramId";

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
        Query query = getEntityManager().createQuery(SQL_SELECT_CHECK_PRICE_BY_ID);
        int sum = 0;
        for (Integer bookId : bookIds) {
            query.setParameter("paramId", bookId);
            ResultSet rs = (ResultSet) query.getResultList();
            try {
                rs.next();
                sum += rs.getInt("price");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return sum;
    }

    //TODO
    @Override
    public Integer findPriceById(Integer id) {
        return null;
    }
}
