package com.test.book.entities;

public record Author(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String biography
) {
}
