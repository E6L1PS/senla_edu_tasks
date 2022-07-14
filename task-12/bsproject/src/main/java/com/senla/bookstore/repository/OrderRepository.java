package com.senla.bookstore.repository;

import com.senla.bookstore.model.Order;
import com.senla.bookstore.repository.interfaces.IOrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderRepository extends AbstractRepository<Integer, Order> implements IOrderRepository<Integer, Order> {

    public OrderRepository() {
        setClazz(Order.class);
    }

    @Override
    public List<Order> findAll() {
        return super.findAll();
    }

    @Override
    public Order findEntityById(Integer id) {
        return super.findEntityById(id);
    }

    @Override
    public boolean create(Order entity) {
        return super.create(entity);
    }

    @Override
    public Order update(Order entity) {
        return super.update(entity);
    }

    @Override
    public boolean delete(Integer id) {
        return super.delete(id);
    }

    @Override
    public boolean delete(Order entity) {
        return super.delete(entity);
    }
}