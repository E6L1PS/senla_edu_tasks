package com.senla.bookstore.repository;

import com.senla.bookstore.model.Order;
import com.senla.bookstore.model.OrderStatus;
import com.senla.bookstore.repository.interfaces.IOrderRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Repository
public class OrderRepository extends AbstractRepository<Integer, Order> implements IOrderRepository<Integer, Order> {

    public OrderRepository() {
        setClazz(Order.class);
    }

    @Override
    public List<Order> findAll() {
        return super.findAll();
    }

    @Override
    public List<Order> findAllByType(String sortType) {
        return super.findAllByType(sortType);
    }

    @Override
    public List<Order> findCompletedByType(LocalDate startDate, LocalDate endDate, String sortType) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> bookRoot = cq.from(Order.class);


        cq.select(bookRoot);
        cq.where(cb.and(
                cb.greaterThan(bookRoot.get("issueDate"), startDate),
                cb.lessThan(bookRoot.get("issueDate"), endDate),
                cb.equal(bookRoot.get("status"), OrderStatus.COMPLETED)
                )
        );
        cq.orderBy(cb.asc(bookRoot.get(sortType)));

        TypedQuery<Order> typedQuery = em.createQuery(cq);

        return typedQuery.getResultList();
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

    @Override
    public Long getQuantityCompletedOrders(LocalDate startDate, LocalDate endDate) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Order> bookRoot = cq.from(Order.class);

        cq.select(cb.count(bookRoot));
        cq.where(cb.and(
                        cb.greaterThan(bookRoot.get("issueDate"), startDate),
                        cb.lessThan(bookRoot.get("issueDate"), endDate),
                        cb.equal(bookRoot.get("status"), OrderStatus.COMPLETED)
                )
        );


        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public Integer getFullPrice(LocalDate startDate, LocalDate endDate) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
        Root<Order> bookRoot = cq.from(Order.class);

        cq.select(cb.sum(bookRoot.get("price")));
        cq.where(cb.and(
                        cb.greaterThan(bookRoot.get("issueDate"), startDate),
                        cb.lessThan(bookRoot.get("issueDate"), endDate),
                        cb.equal(bookRoot.get("status"), OrderStatus.COMPLETED)
                )
        );


        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public void setStatus(Integer id, OrderStatus status) {
        EntityManager em = getEntityManager();

        Query query = em.createQuery("UPDATE Order o SET o.status =: status, o.issueDate =: issueDate WHERE o.id =: id");
        query.setParameter("id", id);
        query.setParameter("status", status);
        query.setParameter("issueDate", LocalDate.now());
        query.executeUpdate();
    }

}