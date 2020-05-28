package org.example.repository;

import org.example.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<Customer, Integer> {
}
