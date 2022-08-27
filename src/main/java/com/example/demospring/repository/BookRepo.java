package com.example.demospring.repository;

import com.example.demospring.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BookRepo extends JpaRepository<Book,Long> {
    @Query("select b from Book b where lower(b.bookName) like %:bookName%")
    List<Book> filterBookName(@Param("bookName") String bookName);

    Optional<Book> findByBookName(String bookName);


}
