package com.senla.bookstore.repository;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Warehouse;
import com.senla.bookstore.repository.interfaces.IWarehouseRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class WarehouseRepository extends AbstractRepository<Integer, Warehouse> implements IWarehouseRepository<Integer, Warehouse> {

    public WarehouseRepository() {
        setClazz(Warehouse.class);
    }
    @Override
    public EntityManager getEntityManager() {
        return super.getEntityManager();
    }

    @Override
    public List<Warehouse> findAll() {
        return super.findAll();
    }

    @Override
    public Warehouse findEntityById(Integer id) {
        return super.findEntityById(id);
    }

    @Override
    public boolean create(Warehouse entity) {
        return super.create(entity);
    }

    @Override
    public Warehouse update(Warehouse entity) {
        return super.update(entity);
    }

    @Override
    public boolean delete(Integer id) {
        return super.delete(id);
    }

    @Override
    public boolean delete(Warehouse entity) {
        return super.delete(entity);
    }
}