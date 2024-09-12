package com.mawuli.author.repositories;

import com.mawuli.author.entities.Author;
import com.mawuli.author.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByName(String name);
    Author findByBooks(List<Book> books);
}
