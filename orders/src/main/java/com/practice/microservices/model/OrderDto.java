package com.practice.microservices.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
    int id;
    LocalDateTime created;
    UserDto customer;
    ProductDto product;
}
