package com.mawuli.author.clients;

import com.mawuli.author.entities.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface BookClient {

    @GetExchange(value = "/books/author/{authorId}")
    List<Book> findByAuthorId(@PathVariable String authorId);
}
