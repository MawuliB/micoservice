package com.mawuli.author.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Converter
public class BookListConverter implements AttributeConverter<List<Book>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Book> books) {
        try {
            return objectMapper.writeValueAsString(books);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting list of books to JSON", e);
        }
    }

    @Override
    public List<Book> convertToEntityAttribute(String booksJson) {
        try {
            return objectMapper.readValue(booksJson, objectMapper.getTypeFactory().constructCollectionType(List.class, Book.class));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}