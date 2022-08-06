package com.example.demospring.controller;

import com.example.demospring.entity.User;
import com.example.demospring.repository.UserRepo;
import com.example.demospring.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserRepo userRepo;
    private final UserServiceImpl userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

}
