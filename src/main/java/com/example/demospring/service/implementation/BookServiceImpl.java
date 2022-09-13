package com.example.demospring.service.implementation;

import com.example.demospring.entity.Book;
import com.example.demospring.entity.dto.BookDto;
import com.example.demospring.repository.BookRepo;
import com.example.demospring.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;

    @Override
    public Book addBook(final BookDto bookDto) {
        bookRepo.findByBookName(bookDto.getBookName()).ifPresent((dto) -> {
            throw new EntityExistsException("Book with such name exists");
        });
        log.info("Saving new book {} to database", bookDto.getBookName());
        final Book book = BookDto.fromDto(bookDto);
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
    public List<BookDto> getBooks() {
        log.info("Fetching all books from database");
        return bookRepo.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
    }
}
