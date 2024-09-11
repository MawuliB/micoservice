package com.test.book.services;

import com.test.book.entities.Book;
import com.test.book.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final String uploadDir = "uploads/";

    public Book createBook(Book book, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            String imagePath = saveImage(imageFile);
            book.setImageUrl(imagePath);
        }
        return bookRepository.save(book);
    }

    private String saveImage(MultipartFile imageFile) throws IOException {
        String fileName = imageFile.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, imageFile.getBytes());
        return filePath.toString();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book updateBook(Book book, Long id) {
        book.setId(id);
        return bookRepository.save(book);
    }

    public Book partialUpdateBook(Book book, Long id){
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook == null) {
            return null;
        }
        if (book.getTitle() != null) {
            existingBook.setTitle(book.getTitle());
        }
        if (book.getAuthor() != null) {
            existingBook.setAuthor(book.getAuthor());
        }
        if (book.getGenreId() != null) {
            existingBook.setGenreId(book.getGenreId());
        }
        if (book.getPageCount() != null) {
            existingBook.setPageCount(book.getPageCount());
        }
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
