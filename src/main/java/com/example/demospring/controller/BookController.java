package com.example.demospring.controller;

import com.example.demospring.entity.Book;
import com.example.demospring.entity.dto.BookDto;
import com.example.demospring.service.implementation.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    private final BookServiceImpl bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getBooks() {
        return ResponseEntity.ok().body(bookService.getBooks());
    }

    @PostMapping("/books/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto book) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/books/save").toUriString());
        return ResponseEntity.created(uri).body(bookService.addBook(book));
    }

    @DeleteMapping ("/books/delete/{bookId}")
    public ResponseEntity<Book> removeBook(@PathVariable Long bookId) {
        return ResponseEntity.ok().body(bookService.deleteBook(bookId));
    }

    @GetMapping("/books/{bookName}")
    public ResponseEntity <List<Book>> findByName(@PathVariable String bookName) {
        return ResponseEntity.ok().body(bookService.getBooksByName(bookName));
    }

}
