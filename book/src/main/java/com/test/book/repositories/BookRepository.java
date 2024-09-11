package com.test.book.repositories;

import com.test.book.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends JpaRepository<Book, Long>, PagingAndSortingRepository<Book, Long> {
}
