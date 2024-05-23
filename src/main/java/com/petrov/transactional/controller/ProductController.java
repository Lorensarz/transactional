package com.petrov.transactional.controller;

import com.petrov.transactional.db.entity.Product;
import com.petrov.transactional.service.ProductService;
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
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product) {
        productService.createProduct(product);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Product getProduct(@PathVariable Long id) {
        return productService.findProduct(id);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
