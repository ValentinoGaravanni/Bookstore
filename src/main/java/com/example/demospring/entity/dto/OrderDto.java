package com.example.demospring.entity.dto;

import com.example.demospring.entity.Book;
import com.example.demospring.entity.User;
import lombok.Data;

@Data
public class OrderDto {
    private Long orderId;
    private double TotalPrice;
    private User owner;
    private Book bought;
}
