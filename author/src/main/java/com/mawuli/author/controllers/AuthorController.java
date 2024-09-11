package com.mawuli.author.controllers;

import com.mawuli.author.entities.Author;
import com.mawuli.author.services.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
@Slf4j
public class AuthorController {

    private final AuthorService authorService;

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
}
