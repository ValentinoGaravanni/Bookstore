package com.example.demospring.entity.dto;

import com.example.demospring.entity.Book;
import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String bookName;
    private String author;
    private double price;
    private int amount;
    private boolean isAvailable;

    public static BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setBookName(book.getBookName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setAvailable(true);
        bookDto.setPrice(book.getPrice());
        bookDto.setAmount(book.getAmount());
        return bookDto;
    }

    public static Book fromDto(BookDto bookDto) {
        Book book = new Book();
        book.setBookName(bookDto.getBookName());
        book.setAvailable(bookDto.isAvailable());
        book.setAmount(bookDto.getAmount());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        return book;
    }
}
