package com.example.demospring.repository;

import com.example.demospring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    @Query(value = "select * from users where username = :username",nativeQuery = true)
    User findByUsername(String username);
}
