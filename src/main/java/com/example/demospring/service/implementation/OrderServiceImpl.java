package com.example.demospring.service.implementation;

import com.example.demospring.entity.Book;
import com.example.demospring.entity.Order;
import com.example.demospring.entity.User;
import com.example.demospring.entity.dto.OrderDto;
import com.example.demospring.repository.BookRepo;
import com.example.demospring.repository.OrderRepo;
import com.example.demospring.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final BookRepo bookRepo;


    @Override
    public Order addToCart(final Long bookId, final User user) {
        final Order order = new Order();
        final Book book = bookRepo.findById(bookId).orElseThrow(() -> new RuntimeException("No such book"));
        order.setOwner(user);
        order.setBuyer(book);
        order.setTotalPrice(book.getPrice());
        orderRepo.save(order);
        return order;
    }

    @Override
    public Order deleteFromCart(final Long orderId) {
        final boolean exists = orderRepo.existsById(orderId);
        if (!exists) {
            throw new IllegalStateException(
                    "Order with id: " + orderId + "doesn't exist!");
        }
        log.info("Deleting order by id: {} from database", orderId);
        orderRepo.deleteById(orderId);
        return null;
    }

    @Override
    public List<OrderDto> getOrders() {
        log.info("Fetching all orders lists from database");
        return orderRepo.findAll().stream().map(OrderDto::toDto).collect(Collectors.toList());
    }
}
