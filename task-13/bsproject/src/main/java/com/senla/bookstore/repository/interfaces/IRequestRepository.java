package com.senla.bookstore.repository.interfaces;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.IEntity;
import com.senla.bookstore.model.Request;

import java.util.List;

public interface IRequestRepository<K extends Number, T extends IEntity> extends IRepository {

    List<T> findAll();

    T findEntityById(K id);

    boolean delete(K id);

    boolean delete(T entity);

    boolean delete(Book entity);

    boolean create(T entity);

    T update(T entity);

}
