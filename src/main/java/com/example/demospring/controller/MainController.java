package com.example.demospring.controller;

import com.example.demospring.entity.Book;
import com.example.demospring.repository.BookRepo;
import com.example.demospring.repository.UserRepo;
import com.example.demospring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    private final BookRepo bookRepo;
    private final UserService userService;

    public MainController(UserRepo userRepo, BookRepo bookRepo, UserService userService) {
        this.bookRepo = bookRepo;
        this.userService = userService;
    }

    @GetMapping("/")
    public List<Book> home (){
        return userService.getBooks();
    }
}