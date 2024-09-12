package com.mawuli.author.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mawuli.author.clients.BookClient;
import com.mawuli.author.entities.Author;
import com.mawuli.author.entities.Book;
import com.mawuli.author.services.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
@Slf4j
public class AuthorController {

    private final AuthorService authorService;

    private final BookClient bookClient;

    private final ObjectMapper objectMapper;

    private List<Book> findByAuthorId(String authorId) {
        RestTemplate restTemplate = new RestTemplate();
        var result = restTemplate.getForObject("http://localhost:8081/books/author/" + authorId, List.class);
        List<Book> books = new ArrayList<>();
        if (result != null) {
            for (Object obj : result) {
                Book book = objectMapper.convertValue(obj, Book.class);
                books.add(book);
            }
        }
        return books;
    }

    private List<Book> findByAuthorId2(String authorId) {
        WebClient webClient = WebClient.create("http://localhost:8081");
        return webClient.get()
                .uri("/books/author/" + authorId)
                .retrieve()
                .bodyToFlux(Book.class)
                .collectList()
                .block();
    }

    @PostMapping("/save")
    public Author saveAuthor(@RequestBody Author author) {
        log.info("Saving author: {}", author.getName());
        return authorService.saveAuthor(author);
    }

    @GetMapping("/get/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        log.info("Getting author by id: {}", id);
        return authorService.getAuthorById(id);
    }

    @GetMapping("/get-by-name")
    public Author getAuthorByName(@RequestParam String name) {
        log.info("Getting author by name: {}", name);
        return authorService.getAuthorByName(name);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAuthorById(@PathVariable Long id) {
        log.info("Deleting author by id: {}", id);
        authorService.deleteAuthorById(id);
    }

    @PutMapping("/update")
    public Author updateAuthor(@RequestBody Author author) {
        log.info("Updating author: {}", author);
        return authorService.updateAuthor(author);
    }

    @GetMapping("/get-all")
    public Iterable<Author> getAllAuthors() {
        log.info("Getting all authors");
        return authorService.getAllAuthors();
    }

    @GetMapping("/get-all-with-books")
    public Iterable<Author> getAllAuthorsWithBooks() {
        log.info("Getting all authors with books");
        Iterable<Author> authors = authorService.getAllAuthors();
        authors.forEach(author -> author.setBooks(findByAuthorId2(author.getId().toString())));
        return authors;
    }
}
