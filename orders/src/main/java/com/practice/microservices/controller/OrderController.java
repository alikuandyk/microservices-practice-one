package com.practice.microservices.controller;

import com.practice.microservices.client.OrderClient;
import com.practice.microservices.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderClient orderClient;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Order order) {
        return orderClient.create(order);
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable int id) {
        return orderClient.findById(id);
    }
}
