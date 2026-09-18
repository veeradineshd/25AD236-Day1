package com.example.springlibrarymanagement.service;

import com.example.springlibrarymanagement.entity.Book;
import com.example.springlibrarymanagement.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(updatedBook.getTitle());
                    existingBook.setAuthor(updatedBook.getAuthor());
                    existingBook.setIsbn(updatedBook.getIsbn());
                    existingBook.setPrice(updatedBook.getPrice());
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}