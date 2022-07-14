package com.senla.bookstore.repository.interfaces;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.IEntity;

import java.util.List;

public interface IWarehouseRepository<K extends Number, T extends IEntity> {


    List<T> findAll();

    T findEntityById(K id);

    boolean delete(K id);

    boolean delete(T entity);

    boolean create(T entity);

    T update(T entity);
}

