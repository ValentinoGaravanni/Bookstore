package com.example.demospring.service;

import com.example.demospring.entity.Order;
import com.example.demospring.entity.User;

import java.util.List;

public interface OrderService {

    Order addToCart(Long bookId , User user);

    Order deleteFromCart(Long orderId);

    List<Order> getOrders();
}
