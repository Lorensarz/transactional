package com.petrov.transactional.service;

import com.petrov.transactional.db.entity.Product;
import com.petrov.transactional.db.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

private final ProductRepository productRepository;

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Товар c id " + id + " не найден!"));
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void updateProductQuantities(List<Product> products) {
        for (Product product : products) {
            if (product.getQuantity() < 1) {
                throw new RuntimeException("Товара нет в наличии : " + product.getProductName());
            }
            product.setQuantity(product.getQuantity() - 1);
            productRepository.save(product);
        }
    }
}
