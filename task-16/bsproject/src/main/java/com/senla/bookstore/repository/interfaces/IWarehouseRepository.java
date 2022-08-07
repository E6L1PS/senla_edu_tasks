package com.senla.bookstore.repository.interfaces;

import com.senla.bookstore.model.IEntity;
import com.senla.bookstore.model.Warehouse;

import java.util.List;

public interface IWarehouseRepository<K extends Number, T extends IEntity> {


    List<T> findAll();

    List<T> findAllByType(String sortType);

    T findEntityById(K id);

    boolean delete(K id);

    boolean delete(T entity);

    boolean create(T entity);

    T update(T entity);

    List<Warehouse> findStaleBooks(String key, String numberMonthForStale);

//    @Transactional
//    @Modifying
//    @Query(value = "delete from Warehouse w where w.book.id = ?1")
//    void deleteByBookId(@Param("book_id") K bookId);
}

