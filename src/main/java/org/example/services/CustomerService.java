package org.example.services;

import org.example.entities.Customer;

import java.util.Optional;

public interface CustomerService {
    void addCustomer(Customer customer);

    void removeCustomer(Customer customer);

    Optional<Customer> getCustomerId(int id);

    Iterable<Customer> getListCustomers();
}
