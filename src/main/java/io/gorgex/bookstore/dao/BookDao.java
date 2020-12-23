package io.gorgex.bookstore.dao;

import io.gorgex.bookstore.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookDao {

    void addBook(UUID id, Book book);

    default void addBook(Book book) {
        UUID id = UUID.randomUUID();
        addBook(id, book);
    }

    List<Book> getAllBooks();

    Optional<Book> getBookById(UUID id);

    int deleteBook(UUID id);

    int updateBook(UUID id, Book book);
}
