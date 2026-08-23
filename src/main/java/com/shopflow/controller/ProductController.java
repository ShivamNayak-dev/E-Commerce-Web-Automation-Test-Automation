package com.shopflow.controller;

import com.shopflow.model.Product;
import com.shopflow.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository products;

    public ProductController(ProductRepository products) {
        this.products = products;
    }

    @GetMapping
    public List<Product> all(@RequestParam(required = false) String keyword) {
        return keyword == null || keyword.isBlank()
                ? products.findAll()
                : products.findByNameContainingIgnoreCase(keyword);
    }

    @GetMapping("/{id}")
    public Product one(@PathVariable Long id) {
        return products.findById(id).orElseThrow();
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return products.save(product);
    }
}
