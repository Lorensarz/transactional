package com.petrov.transactional.service;

import com.petrov.transactional.db.entity.Customer;
import com.petrov.transactional.db.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Клиент с id " + id + " не найден!"));
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Transactional
    public void updateCustomerBalance(Customer customer, double totalAmount) {
        if (customer.getBalance() < totalAmount) {
            throw new RuntimeException("Недостаточный баланс!");
        }
        customer.setBalance(customer.getBalance() - totalAmount);
        customerRepository.save(customer);
    }
}
