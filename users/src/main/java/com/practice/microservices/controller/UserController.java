package com.practice.microservices.controller;

import com.practice.microservices.model.User;
import com.practice.microservices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @PostMapping
    public User create(@RequestBody User user) {
        user.setCreated(LocalDateTime.now());
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow();
    }
}
