package com.mawuli.author.services;

import com.mawuli.author.entities.Author;
import com.mawuli.author.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author getAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

}
