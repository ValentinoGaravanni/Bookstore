package com.example.demospring.service;

import com.example.demospring.entity.Role;
import com.example.demospring.entity.User;
import com.example.demospring.repository.BookRepo;
import com.example.demospring.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;


    @Override
    public void saveUser(User user) {
        Optional<User> userOptional = userRepo
                .findByEmail(user.getEmail());
        if (userOptional.isPresent()){
            throw new IllegalStateException("This email is already taken");
        }
        log.info("Saving new user {} to database", user.getUsername());
        userRepo.save(user);
        user.setRoles(Collections.singleton(Role.USER));
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        boolean exists = userRepo.existsById(userId);
        if(!exists){
            throw new IllegalStateException(
                    "User with id: " + userId + "doesn't exist!");
        }
        log.info("Deleting user by id: {} from database",userId);
        userRepo.deleteById(userId);
    }


    public List<User> getUsers(){
        log.info("Fetching all users from database");
        return userRepo.findAll();
    }

}
