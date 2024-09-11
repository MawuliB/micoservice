package com.test.book.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;

@Converter
public class AuthorConverter implements AttributeConverter<Author, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Author author) {
        try {
            return objectMapper.writeValueAsString(author);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting author to JSON", e);
        }
    }

    @Override
    public Author convertToEntityAttribute(String authorJson) {
        try {
            return objectMapper.readValue(authorJson, Author.class);
        } catch (IOException e) {
            return null;
        }
    }

}
