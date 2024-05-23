package com.petrov.transactional.controller;

import com.petrov.transactional.db.entity.Order;
import com.petrov.transactional.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Order getOrder(@PathVariable Long id) {
        return orderService.findOrder(id);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Order> getAllOrders() {
        return orderService.findAllOrders();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @PostMapping("/placeOrder")
    public void placeOrder(@RequestParam Long customerId, @RequestParam List<Long> productIds) {
        orderService.placeOrder(customerId, productIds);
    }
}
