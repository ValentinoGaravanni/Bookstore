package com.example.demospring.entity.dto;

import com.example.demospring.entity.Order;
import lombok.Data;

@Data
public class OrderDto {
    private Long orderId;
    private double TotalPrice;
    private UserDto buyer;
    private BookDto boughtBook;

    public static Order fromDto(OrderDto orderDto){
        Order order = new Order();
        order.setOwner(UserDto.fromDto(orderDto.getBuyer()));
        order.setBuyer(BookDto.fromDto(orderDto.getBoughtBook()));
        order.setTotalPrice(orderDto.getTotalPrice());
        return order;
    }
    public static OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setBoughtBook(BookDto.toDto(order.getBuyer()));
        orderDto.setBuyer(UserDto.toDto(order.getOwner()));
        return orderDto;

    }
}
