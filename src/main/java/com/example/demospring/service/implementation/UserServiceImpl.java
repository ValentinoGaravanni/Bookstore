package com.example.demospring.service.implementation;

import com.example.demospring.entity.Role;
import com.example.demospring.entity.User;
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

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
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
    public User saveUser(User user) {
        Optional<User> userOptional = userRepo
                .findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("This email is already taken");
        }
        log.info("Saving new user {} to database", user.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return user;
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        boolean exists = userRepo.existsById(userId);
        if (!exists) {
            throw new IllegalStateException(
                    "User with id: " + userId + "doesn't exist!");
        }
        log.info("Deleting user by id: {} from database", userId);
        userRepo.deleteById(userId);
    }

    public List<User> getUsers() {
        log.info("Fetching all users from database");
        return userRepo.findAll();
    }


}
