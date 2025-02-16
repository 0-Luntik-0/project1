package org.example.dao;

import org.example.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> people() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }


    public void save(Person person) {
        String SQL = "INSERT INTO person(fio, year_of_birth) VALUES (?,?)";
        jdbcTemplate.update(SQL, person.getFio(), person.getYearOfBirth());
    }


    public Person personInfo(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id_person = ?",
                        new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findAny().orElse(null);
    }

    public Optional<Person> personInfo(String fio) {
        return jdbcTemplate.query("SELECT * FROM person WHERE fio = ?",
                        new BeanPropertyRowMapper<>(Person.class), fio)
                .stream().findAny();
    }

    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE person SET fio = ?, year_of_birth = ? WHERE id_person = ?",
                updatePerson.getFio(), updatePerson.getYearOfBirth(), id);

    }


}
