package com.example.demospring.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class BookDto {
private Long id;
private String bookName;
private String author;
private double price;
private boolean isAvailable;
}
