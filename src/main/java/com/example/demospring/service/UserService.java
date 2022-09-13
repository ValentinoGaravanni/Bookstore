package com.example.demospring.service;

import com.example.demospring.entity.User;
import com.example.demospring.entity.dto.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User getUser(String username);
    void deleteUser(Long userId);
    List<UserDto>getUsers();
}
