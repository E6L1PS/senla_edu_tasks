package com.senla.bookstore.repository.interfaces;

import com.senla.bookstore.model.IEntity;
import com.senla.bookstore.model.Order;

import java.util.List;

public interface IOrderRepository<K extends Number, T extends IEntity> extends IRepository {

    List<T> findAll();

    Order findEntityById(K id);

    boolean delete(K id);

    boolean delete(T entity);

    boolean create(T entity);

    T update(T entity);

}
