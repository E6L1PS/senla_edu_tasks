package com.senla.bookstore.repository;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.model.Request;
import com.senla.bookstore.repository.interfaces.IRequestRepository;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class RequestRepository extends AbstractRepository<Integer, Request> implements IRequestRepository<Integer, Request> {

    public RequestRepository() {
        setClazz(Request.class);
    }

    @Override
    public List<Request> findAll() {
        return super.findAll();
    }

    @Override
    public Request findEntityById(Integer id) {
        return super.findEntityById(id);
    }

    @Override
    public boolean create(Request entity) {
        return super.create(entity);
    }

    @Override
    public Request update(Request entity) {
        return super.update(entity);
    }

    @Override
    public boolean delete(Integer id) {
        return super.delete(id);
    }

    @Override
    public boolean delete(Request entity) {
        return super.delete(entity);
    }

    public boolean delete(Book entity) {
        Query q = getEntityManager().createQuery("delete FROM Request r WHERE r.book=:id");
        q.setParameter("id", entity);
        q.executeUpdate();
        return true;
    }

}
