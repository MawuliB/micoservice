package com.test.book.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.book.entities.Book;
import com.test.book.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping(value = "/create")
    public Book createBook(@RequestPart("book") String bookStr, @RequestPart("image") MultipartFile imageFile)
            throws IOException {
        Book book = new ObjectMapper().readValue(bookStr, Book.class);
        return bookService.createBook(book, imageFile);
    }

    @PutMapping(value = "/update/{id}")
    public Book updateBook(@RequestPart("book") String bookStr, @PathVariable Long id) throws IOException {
        Book book = new ObjectMapper().readValue(bookStr, Book.class);
        return bookService.updateBook(book, id);
    }

    @GetMapping(value = "/author/{authorId}")
    public List<Book> findByAuthorId(@PathVariable String authorId) {
        // return list of books by authorId
        List<Book> books = new ArrayList<>();
        bookService.findByAuthorId(authorId).forEach(books::add);
        return books;
    }
}
