package org.example.services;

import org.example.entities.Customer;

import java.util.List;

public interface CustomerService {
    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void removeCustomer(Customer customer);

    Customer getCustomerId(int id);

    List<Customer> getListCustomers();
}
