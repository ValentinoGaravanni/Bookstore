package com.example.demospring.controller;

import com.example.demospring.entity.Book;
import com.example.demospring.repository.BookRepo;
import com.example.demospring.service.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
    private final BookRepo bookRepo;
    private final UserServiceImpl userService;

    @GetMapping()
    public List<Book> books (){
        return bookRepo.findAll();
    }

}