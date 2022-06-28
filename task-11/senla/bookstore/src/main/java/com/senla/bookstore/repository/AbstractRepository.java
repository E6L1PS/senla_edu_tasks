package com.senla.bookstore.repository;

import com.senla.bookstore.model.IEntity;

import java.util.List;

public abstract class AbstractRepository<K extends Number, T extends IEntity> {

    public abstract List<T> findAll();

    public abstract T findEntityById(K id);

    public abstract boolean delete(K id);

    public abstract boolean delete(T entity);

    public abstract boolean create(T entity);

    public abstract T update(T entity);
}
