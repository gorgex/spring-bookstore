package io.gorgex.bookstore.dao;

import io.gorgex.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class BookDataAccessService implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addBook(UUID id, Book book) {
        final String sql = "INSERT INTO book (id, name) VALUES (?, ?)";
        jdbcTemplate.update(sql, id, book.getName());
    }

    @Override
    public List<Book> getAllBooks() {
        final String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, mapBookFromDB());
    }

    @Override
    public Optional<Book> getBookById(UUID id) {
        final String sql = "SELECT * FROM book WHERE id = ?";
        Book book = jdbcTemplate.queryForObject(sql, mapBookFromDB(), id);
        return Optional.ofNullable(book);
    }

    @Override
    public int deleteBook(UUID id) {
        final String sql = "DELETE FROM book WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateBook(UUID id, Book book) {
        final String sql = "UPDATE book SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, book.getName(), id);
    }

    private RowMapper<Book> mapBookFromDB() {
        return (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Book(id, name);
        };
    }
}
