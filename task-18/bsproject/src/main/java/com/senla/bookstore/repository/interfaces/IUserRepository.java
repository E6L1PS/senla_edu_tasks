package com.senla.bookstore.repository.interfaces;

import com.senla.bookstore.model.IEntity;

import java.util.List;
import java.util.Optional;

public interface IUserRepository <K extends Number, T extends IEntity> extends IRepository  {

    List<T> findAll();

    T findEntityById(K id);

    Optional<T> findByUserName(String name);

    boolean delete(K id);

    boolean delete(T entity);

    boolean create(T entity);

    T update(T entity);

}
