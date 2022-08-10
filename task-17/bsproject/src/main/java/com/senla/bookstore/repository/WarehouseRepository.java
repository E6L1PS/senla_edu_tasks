package com.senla.bookstore.repository;

import com.senla.bookstore.model.Warehouse;
import com.senla.bookstore.repository.interfaces.IWarehouseRepository;
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
    public List<Warehouse> findAllByType(String sortType) {
        return super.findAllByType(sortType);
    }

    @Override
    public Warehouse findEntityById(Integer id) {
        return super.findEntityById(id);
    }

    public List<Warehouse> findStaleBooks(String sortType, String numberMonthForStale) {
       /* EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT * FROM Warehouse w WHERE w.deliveryDate < :date");
        query.setParameter("date", LocalDate.now().minusMonths(Integer.parseInt(numberMonthForStale)));
*/
        EntityManager em = getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Warehouse> criteriaQuery = criteriaBuilder.createQuery(Warehouse.class);
        Root<Warehouse> bookRoot = criteriaQuery.from(Warehouse.class);


        criteriaQuery.select(bookRoot);
        criteriaQuery.where(criteriaBuilder.lessThan(bookRoot.get("deliveryDate"), LocalDate.now().minusMonths(Integer.parseInt(numberMonthForStale))));
        criteriaQuery.orderBy(criteriaBuilder.asc(bookRoot.get(sortType)));

        TypedQuery<Warehouse> typedQuery = em.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    @Override
    public boolean create(Warehouse entity) {
        return super.create(entity);
    }

    @Override
    public Warehouse update(Warehouse entity) {
        return super.update(entity);
    }

    //TODO delete 1
    @Override
    public boolean delete(Integer id) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("DELETE FROM Warehouse w WHERE w.book.id = :book_id");
        query.setParameter("book_id", id);
        query.executeUpdate();

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Warehouse> cq = cb.createQuery(Warehouse.class);
//        Root<Warehouse> root = cq.from(Warehouse.class);
//        cq.where(cb.equal(root.get("book"), "id"));
//
//        List<Warehouse> list = em.createQuery(cq).getResultList();
//
//        return list.isEmpty();
        return false;
    }

    @Override
    public boolean delete(Warehouse entity) {
        return super.delete(entity);
    }
}