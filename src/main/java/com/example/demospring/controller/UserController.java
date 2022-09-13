package com.example.demospring.controller;

import com.example.demospring.entity.User;
import com.example.demospring.entity.dto.UserDto;
import com.example.demospring.service.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/users/save")
    public ResponseEntity<HttpStatus> saveUser(@RequestBody UserDto user){
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName){
        return ResponseEntity.ok().body(userService.getUser(userName));
    }

}
