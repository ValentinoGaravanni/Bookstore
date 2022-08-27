package com.example.demospring.service;

import com.example.demospring.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUser(String username);
    void deleteUser(Long userId);
    List<User>getUsers();
}
