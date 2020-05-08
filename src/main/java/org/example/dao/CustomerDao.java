package org.example.dao;

import org.example.entities.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.function.Consumer;

public class CustomerDao {
    private EntityManager entityManager;

    public void addCustomer(Customer customer) {
        getTransaction(entityManager1 -> entityManager1.persist(customer));
    }

    public void updateCustomer(Customer customer, String newCustomer) {
        customer.setName(newCustomer);
        getTransaction(entityManager1 -> entityManager1.merge(customer));
    }

    public void removeCustomer(Customer customer) {
        getTransaction(entityManager1 -> entityManager1.remove(entityManager1.contains(customer) ? customer : entityManager1.merge(customer)));
    }

    public void getCustomerId(int id) {
        getTransaction(entityManager1 -> entityManager1.find(Customer.class, id));
    }

    public List<Customer> getListCustomers() {
        entityManager = Persistence.createEntityManagerFactory("entityManager").createEntityManager();
        List<Customer> customers = entityManager.createNamedQuery("Customer").getResultList();
        entityManager.close();
        return customers;
    }

    private void getTransaction(Consumer<EntityManager> consumer) {
        entityManager = Persistence.createEntityManagerFactory("entityManager").createEntityManager();
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
