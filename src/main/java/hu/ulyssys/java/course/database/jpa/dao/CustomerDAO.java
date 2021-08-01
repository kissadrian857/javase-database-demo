package hu.ulyssys.java.course.database.jpa.dao;

import hu.ulyssys.java.course.database.jpa.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerDAO {
    private static final String PERSISTENCE_UNIT = "TestPersistence";

    private EntityManager createEntityManager() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
    }

    public void save(Customer customer) {
        EntityManager entityManager = createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(Customer customer) {
        EntityManager entityManager = createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(customer);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Customer> findAll() {
        TypedQuery<Customer> query = createEntityManager().createQuery("select n from Customer n", Customer.class);
        return query.getResultList();
    }

    //Delete és findById
}
