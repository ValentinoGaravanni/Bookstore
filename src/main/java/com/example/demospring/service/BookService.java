package com.example.demospring.service;

import com.example.demospring.entity.Book;
import com.example.demospring.entity.dto.BookDto;

import java.util.List;

public interface BookService {
    Book addBook(BookDto bookDto);


    Book getBook(String bookName);

   void deleteBook(Long bookId);

    List<Book> getBooks();
}
