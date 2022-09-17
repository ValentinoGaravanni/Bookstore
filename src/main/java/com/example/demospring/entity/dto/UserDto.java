package com.example.demospring.entity.dto;

import com.example.demospring.entity.Role;
import com.example.demospring.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Collections;

@Data
public class UserDto {
    private Long id;
    private String username;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private String email;

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;

    }
    public static User fromDto(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        return user;
    }
}
