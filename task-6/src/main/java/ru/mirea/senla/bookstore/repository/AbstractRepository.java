package ru.mirea.senla.bookstore.repository;

import ru.mirea.senla.bookstore.model.IEntity;
import ru.mirea.senla.bookstore.repository.interfaces.IAbstractRepository;

import java.io.Serializable;
import java.util.List;

public class AbstractRepository<T extends IEntity> implements IAbstractRepository {

    @Override
    public Object getEntityById(Serializable id) {
        return null;
    }

    @Override
    public List getEntities() {
        return null;
    }

    @Override
    public void add(Object entity) {

    }

    @Override
    public Object update(Object entity) {
        return null;
    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public void deleteById(Serializable id) {

    }
}
