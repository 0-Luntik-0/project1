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
        return jdbcTemplate.query(SQL, (rs, rowNum) -> {
            Book book = new Book();
            book.setIdBook(rs.getInt("id_book"));

            // Обрабатываем NULL в id_person правильно
            Integer idPerson = rs.getObject("id_person", Integer.class);
            book.setIdPerson(idPerson); // Теперь если NULL, останется NULL

            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            book.setYear(rs.getInt("year"));
            return book;
        });
    }


    public Book bookInfo(int id) {
        String SQL = "SELECT b.id_book, b.name, b.author, b.year, p.id_person, p.fio " +
                " AS fio FROM book b LEFT JOIN person p ON b.id_person = p.id_person WHERE b.id_book = ?";
        return jdbcTemplate.query(SQL,
                        new BeanPropertyRowMapper<>(Book.class), id)
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        String SQL = "INSERT INTO book(name, author, year, id_person) VALUES (?, ?, ?, ?)";
        Integer idPerson = book.getIdPerson();
        jdbcTemplate.update(SQL,
                book.getName(),
                book.getAuthor(),
                book.getYear(),
                (idPerson != null) ? book.getIdPerson() : null);
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

    public void assignToPerson(int bookId, int personId) {
        jdbcTemplate.update("UPDATE book SET id_person = ? WHERE id_book = ?", personId, bookId);
    }

    public void deleteIdPerson(int bookId) { // освобождаем книгу
        jdbcTemplate.update("UPDATE book SET id_person = NULL WHERE id_book = ?", bookId);

    }

}
