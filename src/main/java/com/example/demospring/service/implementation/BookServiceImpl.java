package com.example.demospring.service.implementation;

import com.example.demospring.entity.Book;
import com.example.demospring.entity.dto.BookDto;
import com.example.demospring.repository.BookRepo;
import com.example.demospring.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;

    @Override
    public Book addBook(final BookDto bookDto) {
        bookRepo.findByBookName(bookDto.getBookName()).orElseThrow(() -> new IllegalStateException("Book with such name exists"));
        log.info("Saving new book {} to database", bookDto.getBookName());
        final Book book = new Book();
        book.setBookName(bookDto.getBookName());
        book.setAuthor(bookDto.getAuthor());
        book.setAvailable(true);
        book.setPrice(bookDto.getPrice());
        book.setAmount(bookDto.getAmount());
        bookRepo.save(book);
        return book;
    }

    @Override
    public List<Book> getBooksByName(final String bookName) {
        return bookRepo.filterBookName(bookName.toLowerCase());
    }

    @Override
    public Book deleteBook(final Long bookId) {
        final boolean exists = bookRepo.existsById(bookId);
        if (!exists) {
            throw new IllegalStateException(
                    "User with id: " + bookId + "doesn't exist!");
        }
        log.info("Deleting user by id: {} from database", bookId);
        bookRepo.deleteById(bookId);
        return null;
    }


    @Override
    public List<Book> getBooks() {
        log.info("Fetching all books from database");
        return bookRepo.findAll();
    }
}
