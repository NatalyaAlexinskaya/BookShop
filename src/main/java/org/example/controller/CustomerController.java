package org.example.controller;

import org.example.entities.Customer;
import org.example.repository.CustomerDao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "customer")
public class CustomerController {
    private CustomerDao customerDao;

    public CustomerController(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @GetMapping(path = "/all")
    public List<Customer> getCustomers() {
        return customerDao.getListCustomers();
    }

    @GetMapping(path = "/{id}")
    public Customer getById(@PathVariable(name = "id") Integer id) {
        return customerDao.getCustomerId(id);
    }

    @PostMapping(path = "/add")
    public void addCustomer(@RequestBody Customer customer) {
        customerDao.addCustomer(customer);
    }

    @PutMapping(path = "/update")
    public void updateCustomer(@RequestBody Customer customer) {
        customerDao.updateCustomer(customer);
    }

    @DeleteMapping(path = "/remove")
    public void removeCustomer(@RequestBody Customer customer) {
        customerDao.removeCustomer(customer);
    }
}

