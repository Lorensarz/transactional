package com.petrov.transactional.controller;

import com.petrov.transactional.db.entity.Customer;
import com.petrov.transactional.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.findCustomer(id);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
