package com.mawuli.author.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String biography;

    @Convert(converter = BookListConverter.class)
    private List<Book> books = new ArrayList<>();
}
