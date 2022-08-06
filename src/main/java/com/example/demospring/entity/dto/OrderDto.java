package com.example.demospring.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private double TotalPrice;
}
