package com.petrov.transactional.db.repository;

import com.petrov.transactional.db.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
