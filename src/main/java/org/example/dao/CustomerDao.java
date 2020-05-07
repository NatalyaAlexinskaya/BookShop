package org.example.dao;

import org.example.Customer;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomerDao {
    private EntityManager entityManager;

    public CustomerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addCustomer(Customer customer) {
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
    }

    public void updateCustomer(Customer customer, String newCustomer) {
        entityManager.getTransaction().begin();
        customer.setName(newCustomer);
        entityManager.merge(customer);
        entityManager.getTransaction().commit();
    }

    public void removeCustomer(Customer customer) {
        entityManager.getTransaction().begin();
        entityManager.remove(customer);
        entityManager.getTransaction().commit();
    }

    public Customer getCustomerId(int id) {
        entityManager.getTransaction().begin();
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.getTransaction().commit();

        return customer;
    }

    public List<Customer> getListCustomers() { return entityManager.createNamedQuery("Customer").getResultList(); }
}
