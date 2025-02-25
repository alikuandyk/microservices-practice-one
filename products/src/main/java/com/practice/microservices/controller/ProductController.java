package com.practice.microservices.controller;

import com.practice.microservices.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final List<Product> products = new ArrayList<>();
    private int nextId = 1;

    @PostMapping
    public Product create(@RequestBody Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }
}
