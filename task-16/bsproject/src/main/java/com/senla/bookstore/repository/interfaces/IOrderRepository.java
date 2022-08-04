package com.senla.bookstore.repository.interfaces;

import com.senla.bookstore.model.IEntity;
import com.senla.bookstore.model.Order;
import com.senla.bookstore.model.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface IOrderRepository<K extends Number, T extends IEntity> extends IRepository {

    List<T> findAll();

    List<T> findAllByType(String sortType);

    List<T> findCompletedByType (LocalDate startDate, LocalDate endDate, String sortType);

    Long getQuantityCompletedOrders(LocalDate startDate, LocalDate endDate);

    K getFullPrice(LocalDate startDate, LocalDate endDate);

    Order findEntityById(K id);

    boolean delete(K id);

    boolean delete(T entity);

    boolean create(T entity);

    T update(T entity);

    void setStatus(K id, OrderStatus status);
}
