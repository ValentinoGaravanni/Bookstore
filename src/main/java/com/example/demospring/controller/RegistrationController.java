package com.example.demospring.controller;

import com.example.demospring.repository.UserRepo;
import com.example.demospring.service.UserService;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class RegistrationController {
    private final UserRepo userRepo;
    private final UserService userService;



}
