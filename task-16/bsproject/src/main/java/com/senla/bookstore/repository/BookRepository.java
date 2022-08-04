package com.senla.bookstore.repository;

import com.senla.bookstore.model.Book;
import com.senla.bookstore.repository.interfaces.IBookRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookRepository extends AbstractRepository<Integer, Book> implements IBookRepository<Integer, Book> {

    public BookRepository() {
        setClazz(Book.class);
    }

    @Override
    public List<Book> findAll() {
        return super.findAll();
    }

    @Override
    public List<Book> findAllByType(String sortType) {
        return super.findAllByType(sortType);
    }

    @Override
    public Book findEntityById(Integer id) {
        return super.findEntityById(id);
    }

    @Override
    public boolean create(Book entity) {
        return super.create(entity);
    }

    @Override
    public Book update(Book entity) {
        return super.update(entity);
    }

    @Override
    public boolean delete(Integer id) {
        return super.delete(id);
    }

    @Override
    public boolean delete(Book entity) {
        return super.delete(entity);
    }

    @Override
    public Integer checkPrice(List<Book> books) {
        int sum = 0;

        for (Book book : books) {
            sum += findPriceById(book.getId());
        }

        return sum;
    }

    @Override
    public String findDescriptionById(Integer id) {
        TypedQuery<String> q = getEntityManager().createQuery("select description from Book b WHERE b.id=:id", String.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    @Override
    public Integer findPriceById(Integer id) {
        TypedQuery<Integer> q = getEntityManager().createQuery("select price from Book b WHERE b.id=:id", Integer.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }
}
