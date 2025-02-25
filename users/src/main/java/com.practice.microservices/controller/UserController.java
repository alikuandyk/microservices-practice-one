package com.practice.microservices.controller;

import com.practice.microservices.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final List<User> users = new ArrayList<>();
    private int nextId = 1;

    @PostMapping
    public User create(@RequestBody User user) {
        user.setId(nextId++);
        user.setCreated(LocalDateTime.now());
        users.add(user);
        return user;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow();
    }
}
