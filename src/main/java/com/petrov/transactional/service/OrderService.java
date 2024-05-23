package com.petrov.transactional.service;

import com.petrov.transactional.db.entity.Customer;
import com.petrov.transactional.db.entity.Order;
import com.petrov.transactional.db.entity.Product;
import com.petrov.transactional.db.repository.CustomerRepository;
import com.petrov.transactional.db.repository.OrderRepository;
import com.petrov.transactional.db.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    public void createOrder(Order order) {
        orderRepository.save(order);
    }

    public Order findOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Заказ с id " + id + " не найден!"));
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public void placeOrder(Long customerId, List<Long> productIds) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Клиент не найден!"));

        List<Product> products = productRepository.findAllById(productIds);
        double totalAmount = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("Customer: " + customer);
        System.out.println("Products: " + products);
        System.out.println("Total Amount: " + totalAmount);

        customerService.updateCustomerBalance(customer, totalAmount);
        productService.updateProductQuantities(products);
        createOrder(customer, products, totalAmount);
    }

    @Transactional
    public void createOrder(Customer customer, List<Product> products, double totalAmount) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setProducts(products);
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);
    }
}
