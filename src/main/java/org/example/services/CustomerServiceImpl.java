package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entities.Customer;
import org.example.repository.CustomerDao;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;

    @Override
    public void addCustomer(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public void removeCustomer(Customer customer) {
        customerDao.delete(customer);
    }

    @Override
    public Optional<Customer> getCustomerId(int id) {
        return customerDao.findById(id);
    }

    @Override
    public Iterable<Customer> getListCustomers() {
        return customerDao.findAll();
    }
}
