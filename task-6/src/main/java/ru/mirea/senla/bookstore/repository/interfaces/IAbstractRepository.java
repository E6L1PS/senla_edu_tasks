package ru.mirea.senla.bookstore.repository.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IAbstractRepository <T> {

    T getEntityById(Serializable id);

    List<T> getEntities();

    void add(T entity);

    T update(T entity);

    void delete(T entity);

    void deleteById(Serializable id);

}
