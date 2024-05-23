package com.petrov.transactional.db.repository;

import com.petrov.transactional.db.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
