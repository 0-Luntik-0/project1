package org.example.dao;

import org.example.models.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> list() {
        String SQL = "SELECT id_book, id_person, name, author, year FROM book";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Book.class));
    }

    public Book bookInfo(int id) {
        String SQL = "SELECT id_book, id_person, name, author, year FROM book WHERE id_book = ?";
        return jdbcTemplate.query(SQL,
                        new BeanPropertyRowMapper<>(Book.class), id)
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name,author,year) VALUES (?,?,?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void edit(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET name = ?, author = ?, year = ? WHERE id_book = ?",
                book.getName(), book.getAuthor(), book.getYear(), id);

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id_book= ?", id);
    }

    public List<Book> getBooksByPerson(int id_person) {
        String SQL = "SELECT id_book, id_person, name, author, year FROM book WHERE id_person = ?";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Book.class), id_person);
    }
}
