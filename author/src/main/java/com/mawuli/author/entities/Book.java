package com.mawuli.author.entities;

import lombok.Builder;

@Builder
public record Book(
    Long id,
    String title,
    String authorId,
    String genreId,
    Integer pageCount,
    String imageUrl
) {
}
