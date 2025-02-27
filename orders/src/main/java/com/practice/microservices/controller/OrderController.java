package com.practice.microservices.controller;

import com.practice.microservices.model.Order;
import com.practice.microservices.model.ProductDto;
import com.practice.microservices.model.UserDto;
import com.practice.microservices.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;
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

        order.setCreated(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable int id) {
        return orderRepository.findById(id).orElseThrow();
    }
}
