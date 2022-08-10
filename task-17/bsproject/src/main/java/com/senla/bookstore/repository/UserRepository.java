package com.senla.bookstore.repository;

import com.senla.bookstore.model.User;
import com.senla.bookstore.repository.interfaces.IUserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository extends AbstractRepository<Integer, User> implements IUserRepository<Integer, User> {

    @Override
    public EntityManager getEntityManager() {
        return super.getEntityManager();
    }

    @Override
    public List<User> findAll() {
        return super.findAll();
    }

    @Override
    public List<User> findAllByType(String sortType) {
        return super.findAllByType(sortType);
    }

    @Override
    public User findEntityById(Integer id) {
        return super.findEntityById(id);
    }

    @Override
    public Optional<User> findByUserName(@NonNull String name) {
        TypedQuery<User> tq = getEntityManager().createQuery("SELECT u FROM User u WHERE u.username =: name", User.class);
        tq.setParameter("name", name);

        return Optional.ofNullable(tq.getSingleResult());
    }

    @Override
    public boolean create(@NonNull User entity) {
        return super.create(entity);
    }

    @Override
    public User update(User entity) {
        return super.update(entity);
    }

    @Override
    public boolean delete(Integer id) {
        return super.delete(id);
    }

    @Override
    public boolean delete(User entity) {
        return super.delete(entity);
    }
}
