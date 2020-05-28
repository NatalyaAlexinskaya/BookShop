package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entities.Customer;
import org.example.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "customer")
public class CustomerController {
    private CustomerService customerService;

    @GetMapping(path = "/all")
    public Iterable<Customer> getCustomers() {
        return customerService.getListCustomers();
    }

    @GetMapping(path = "/{id}")
    public Optional<Customer> getById(@PathVariable(name = "id") Integer id) {
        return customerService.getCustomerId(id);
    }

    @PostMapping(path = "/add")
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }

    @DeleteMapping(path = "/remove")
    public void removeCustomer(@RequestBody Customer customer) {
        customerService.removeCustomer(customer);
    }
}

