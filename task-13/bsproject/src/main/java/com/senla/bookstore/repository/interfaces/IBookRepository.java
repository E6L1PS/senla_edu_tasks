package com.senla.bookstore.repository.interfaces;


import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IBookRepository<K extends Number, T extends IEntity> {

    List<T> findAll();

    Book findEntityById(K id);

    boolean delete(K id);

    boolean delete(T entity);

    boolean create(T entity);

    T update(T entity);

    K checkPrice(List<K> bookIds);

    @Query(
            value = "SELECT price FROM Book b WHERE b.id = :id",
            nativeQuery = true
    )
    K findPriceById(@Param("id") K id);
}
