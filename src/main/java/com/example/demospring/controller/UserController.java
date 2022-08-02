package com.example.demospring.controller;

import com.example.demospring.entity.User;
import com.example.demospring.repository.UserRepo;
import com.example.demospring.service.UserService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
public class UserController {
    private final UserRepo userRepo;
    private final UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @PostMapping
    public void registerUser(@RequestBody User user){
        userService.addNewUser(user);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(
            @PathVariable("studentId") Long studentId){
        userService.deleteUser(studentId);
    }
}
