package com.petrov.transactional.db.repository;

import com.petrov.transactional.db.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
