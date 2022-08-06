package com.example.demospring.repository;

import com.example.demospring.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepo extends JpaRepository<Book,Long> {
    Book findByBookName(String bookName);
}
