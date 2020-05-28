package org.example.services;

import org.example.entities.Customer;
import org.example.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void addCustomer(Customer customer) {
        customerDao.addCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }

    @Override
    public void removeCustomer(Customer customer) {
        customerDao.removeCustomer(customer);
    }

    @Override
    public Customer getCustomerId(int id) {
        return customerDao.getCustomerId(id);
    }

    @Override
    public List<Customer> getListCustomers() {
        return customerDao.getListCustomers();
    }
}
