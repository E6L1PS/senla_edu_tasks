package com.senla.bookstore.repository;

import com.senla.bookstore.model.Author;
import com.senla.bookstore.repository.interfaces.IAuthorRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AuthorRepository extends AbstractRepository<Integer, Author> implements IAuthorRepository<Integer, Author> {

    public AuthorRepository() {
        setClazz(Author.class);
    }

    @Override
    public List<Author> findAll() {
        return super.findAll();
    }

    @Override
    public Author findEntityById(Integer id) {
        return super.findEntityById(id);
    }

    @Override
    public boolean create(Author entity) {
        return super.create(entity);
    }

    @Override
    public Author update(Author entity) {
        return super.update(entity);
    }

    @Override
    public boolean delete(Integer id) {
        return super.delete(id);
    }

    @Override
    public boolean delete(Author entity) {
        return super.delete(entity);
    }
}
