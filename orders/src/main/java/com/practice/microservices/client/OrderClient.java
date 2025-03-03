package com.practice.microservices.client;

import com.practice.microservices.ErrorResponse;
import com.practice.microservices.NotFoundException;
import com.practice.microservices.model.Order;
import com.practice.microservices.model.OrderDto;
import com.practice.microservices.model.ProductDto;
import com.practice.microservices.model.UserDto;
import com.practice.microservices.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrderClient {
    @Value("${users-server-url}")
    private String usersUrl;
    @Value("${products-server-url}")
    private String productsUrl;
    protected final RestTemplate rest = new RestTemplate();
    private final OrderRepository orderRepository;

    public ResponseEntity<Object> create(Order order) {
        try {
            UserDto user = rest.getForObject(usersUrl + "/" + order.getCustomerId(), UserDto.class);
            ProductDto product = rest.getForObject(productsUrl + "/" + order.getProductId(), ProductDto.class);

            order.setCreated(LocalDateTime.now());
            orderRepository.save(order);

            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setCreated(order.getCreated());
            orderDto.setCustomer(user);
            orderDto.setProduct(product);

            return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
        } catch (HttpClientErrorException ex) {
            ErrorResponse errorResponse = ex.getResponseBodyAs(ErrorResponse.class);
            return new ResponseEntity<>(errorResponse, ex.getStatusCode());
        }
    }

    public Order findById(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Заказ с id=" + orderId + " не найден"));
    }
}
