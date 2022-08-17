package com.example.demospring.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {
private Long id;
private String bookName;
private String author;
private double price;
private int amount;
private boolean isAvailable;
}
