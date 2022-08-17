package com.example.demospring.repository;

import com.example.demospring.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookRepo extends JpaRepository<Book,Long> {
    Optional<Book> findByBookName(String bookName);
}
