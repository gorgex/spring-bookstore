package io.gorgex.bookstore.service;

import io.gorgex.bookstore.dao.BookDao;
import io.gorgex.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    private final BookDao bookDao;

    @Autowired
    public BookService(@Qualifier("postgres") BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public Optional<Book> getBookById(UUID id) {
        return bookDao.getBookById(id);
    }

    public int deleteBook(UUID id) {
        return bookDao.deleteBook(id);
    }

    public int updateBook(UUID id, Book book) {
        return bookDao.updateBook(id, book);
    }
}
