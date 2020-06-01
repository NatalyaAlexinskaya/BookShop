package org.example.dao;

import org.example.entities.Customer;
import org.example.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.function.Consumer;

public class CustomerDaoImpl implements CustomerDao {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public CustomerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addCustomer(Customer customer) {
        getTransaction(entityManager1 -> entityManager1.persist(customer));
    }

    @Override
    public void updateCustomer(Customer customer) {
        getTransaction(entityManager1 -> entityManager1.merge(customer));
    }

    @Override
    public void removeCustomer(Customer customer) {
        getTransaction(entityManager1 -> entityManager1.remove(entityManager1.contains(customer) ? customer : entityManager1.merge(customer)));
    }

    @Override
    public Customer getCustomerId(int id) {
        entityManager = entityManagerFactory.createEntityManager();
        Customer customer = entityManager.createNamedQuery("Customer.ID", Customer.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.close();
        return customer;
    }

    @Override
    public List<Customer> getListCustomers() {
        entityManager = entityManagerFactory.createEntityManager();
        List<Customer> customers = entityManager.createNamedQuery("Customer").getResultList();
        entityManager.close();
        return customers;
    }

    private void getTransaction(Consumer<EntityManager> consumer) {
        entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            consumer.accept(entityManager);
            if (transaction.isActive()) {
                transaction.commit();
            }
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
