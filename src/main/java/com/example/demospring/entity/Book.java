package com.example.demospring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
@Getter
@Setter
public class Book {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long bookId;
    @Size(min = 2, message = "Not enough, more symbols are needed")
    private String bookName;
    @Size(min = 2 , message = "Error of naming")
    private String author;
    @NotBlank(message = "You are really generous.But still put the price!")
    private double price;
    private boolean isAvailable;

    @OneToMany(mappedBy = "buyer",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders;

}
