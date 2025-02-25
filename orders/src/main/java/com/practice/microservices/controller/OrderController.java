package com.practice.microservices.controller;

import com.practice.microservices.model.Order;
import com.practice.microservices.model.ProductDto;
import com.practice.microservices.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final List<Order> orders = new ArrayList<>();
    private int nextId = 1;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${users-server-url}")
    private String usersUrl;

    @Value("${products-server-url}")
    private String productsUrl;


    @PostMapping
    public Order create(@RequestBody Order order) {
        UserDto userDto = restTemplate.getForObject(usersUrl + "/" + order.getCustomerId(), UserDto.class);
        if (userDto == null) {
            throw new NoSuchElementException("Пользователь не найден");
        }

        ProductDto productDto = restTemplate.getForObject(productsUrl + "/" + order.getProductId(), ProductDto.class);
        if (productDto == null) {
            throw new NoSuchElementException("Продукт не найден");
        }

        order.setId(nextId++);
        order.setCreated(LocalDateTime.now());
        orders.add(order);
        return order;
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable int id) {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElseThrow();
    }
}
