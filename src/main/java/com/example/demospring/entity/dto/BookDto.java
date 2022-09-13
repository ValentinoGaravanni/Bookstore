package com.example.demospring.entity.dto;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String bookName;
    private String author;
    private double price;
    private int amount;
    private boolean isAvailable;
}
