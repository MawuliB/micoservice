package com.test.book.entities;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Convert(converter = AuthorConverter.class)
    private Author author;

    private String genreId;

    private Integer pageCount;

    private String imageUrl;
}
