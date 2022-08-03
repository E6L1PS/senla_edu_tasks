package com.senla.bookstore.repository;

import com.senla.bookstore.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository extends AbstractRepository<Integer, Customer> {

    public CustomerRepository() {
        setClazz(Customer.class);
    }

    @Override
    public List<Customer> findAll() {
        return super.findAll();
    }

    @Override
    public Customer findEntityById(Integer id) {
        return super.findEntityById(id);
    }

    @Override
    public boolean create(Customer entity) {
        return super.create(entity);
    }

    @Override
    public Customer update(Customer entity) {
        return super.update(entity);
    }

    @Override
    public boolean delete(Integer id) {
        return super.delete(id);
    }

    @Override
    public boolean delete(Customer entity) {
        return super.delete(entity);
    }
}
