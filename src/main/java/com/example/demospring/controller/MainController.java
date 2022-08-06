package com.example.demospring.controller;

import com.example.demospring.repository.BookRepo;
import com.example.demospring.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final BookRepo bookRepo;
    private final UserServiceImpl userService;



}