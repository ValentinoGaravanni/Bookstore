package com.example.demospring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Size(min = 2,message = "Not enough, more symbols are needed")
    private double TotalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "bookId")
    private Book buyer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "userId")
    private User owner;

}
