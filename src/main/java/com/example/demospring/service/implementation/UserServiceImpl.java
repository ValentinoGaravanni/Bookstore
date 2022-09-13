package com.example.demospring.service.implementation;

import com.example.demospring.entity.Role;
import com.example.demospring.entity.User;
import com.example.demospring.entity.dto.UserDto;
import com.example.demospring.repository.UserRepo;
import com.example.demospring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userRepo.findByUsername(username);
        if (user == null) {
            log.info("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in database : {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public void saveUser(final UserDto userDto) {
        userRepo.findByEmail(userDto.getEmail()).ifPresent(dto -> {
            throw new EntityExistsException("There is a user with such email");
        });
        final User user = UserDto.fromDto(userDto);
        log.info("Saving new user {} to database", userDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userRepo.save(user);
    }

    @Override
    public User getUser(final String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public void deleteUser(final Long userId) {
        final boolean exists = userRepo.existsById(userId);
        if (!exists) {
            throw new IllegalStateException(
                    "User with id: " + userId + "doesn't exist!");
        }
        log.info("Deleting user by id: {} from database", userId);
        userRepo.deleteById(userId);
    }

    public List<UserDto> getUsers() {
        log.info("Fetching all users from database");
        return userRepo.findAll().stream().map(UserDto::toDto).collect(Collectors.toList());
    }


}
