package com.example.demospring.service;

import com.example.demospring.entity.Book;
import com.example.demospring.entity.User;
import com.example.demospring.repository.BookRepo;
import com.example.demospring.repository.UserRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class UserService {
    private final UserRepo userRepo;
    private final BookRepo bookRepo;

    public UserService(UserRepo userRepo, BookRepo bookRepo){
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }

    public List<Book> getBooks(){
        return bookRepo.findAll();
    }
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public User addUser(User user){
        return userRepo.save(user);
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepo
                .findByEmail(user.getEmail());
        if (userOptional.isPresent()){
            throw new IllegalStateException("This email is already taken");
        }
        userRepo.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepo.existsById(userId);
        if(!exists){
            throw new IllegalStateException(
                    "User with id: " + userId + "doesn't exist!");
        }
        userRepo.deleteById(userId);
    }
}
