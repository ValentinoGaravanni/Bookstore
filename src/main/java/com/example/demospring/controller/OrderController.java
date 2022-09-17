package com.example.demospring.controller;

import com.example.demospring.entity.Order;
import com.example.demospring.entity.User;
import com.example.demospring.entity.dto.OrderDto;
import com.example.demospring.repository.UserRepo;
import com.example.demospring.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserRepo userRepo;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getBooks() {
        return ResponseEntity.ok().body(orderService.getOrders());
    }

    @PostMapping("/orders/add/{bookId}")
    public ResponseEntity<Order> addBookToCart(@PathVariable Long bookId) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/orders/add").toUriString());
        User user = userRepo.findById(2L).orElseThrow(()->new RuntimeException("blablala"));
        return ResponseEntity.created(uri).body(orderService.addToCart(bookId, user));
    }

    @DeleteMapping("/orders/delete/{orderId}")
    public ResponseEntity<Order> removeOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(orderService.deleteFromCart(orderId));
    }
}