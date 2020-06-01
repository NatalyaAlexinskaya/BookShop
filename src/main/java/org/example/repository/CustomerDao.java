package org.example.repository;

import org.example.entities.Customer;

import java.util.List;

public interface CustomerDao {
    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void removeCustomer(Customer customer);

    Customer getCustomerId(int id);

    List<Customer> getListCustomers();
}
