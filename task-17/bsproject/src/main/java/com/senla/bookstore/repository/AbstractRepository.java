package com.senla.bookstore.repository;

import com.senla.bookstore.model.IEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

public abstract class AbstractRepository<K extends Number, T extends IEntity> {

    private Class<T> clazz;

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("FROM " + clazz.getName()).getResultList();
    }

    public List<T> findAllByType(String sortType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> bookRoot = criteriaQuery.from(clazz);

        criteriaQuery.select(bookRoot);
        criteriaQuery.orderBy(criteriaBuilder.asc(bookRoot.get(sortType)));

        TypedQuery<T> typedQuery = getEntityManager().createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    public T findEntityById(K id) {
        return entityManager.find(clazz, id);
    }

    public boolean create(T entity) {
        entityManager.persist(entity);
        return true;
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public boolean delete(K id) {
        entityManager.remove(findEntityById(id));
        return true;
    }

    public boolean delete(T entity) {
        entityManager.remove(entity);
        return true;
    }
}
