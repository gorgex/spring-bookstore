package io.gorgex.bookstore.controller;

import io.gorgex.bookstore.model.Book;
import io.gorgex.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/books")
@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public void addBook(@NonNull @RequestBody Book book) {
        bookService.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "{id}")
    public Book getBookById(@PathVariable("id") UUID id) {
        return bookService.getBookById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public int deleteBook(@PathVariable("id") UUID id) {
        return bookService.deleteBook(id);
    }

    @PutMapping(path = "{id}")
    public int updateBook(@PathVariable("id") UUID id, @NonNull @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }
}
