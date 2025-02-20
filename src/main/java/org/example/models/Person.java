package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private int idPerson;

    @Column(name = "fio")
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-z]+\\s[А-ЯЁA-Z][а-яёa-z]+(?:\\s[А-ЯЁA-Z][а-яёa-z]+)?$",
            message = "Введите минимум имя и фамилию, допускается до 3 слов. Только буквы и пробел😊")
    private String fio;

    @Column(name = "year_of_birth")
    @NotNull(message = "Введите год рожедения")
    @PastOrPresent(message = "Вы не можете родиться в будующем")
    private LocalDate yearOfBirth;

    @OneToMany(mappedBy = "person")
    private List<Book> books = new ArrayList<>();

    public Person(int idPerson, String fio, LocalDate yearOfBirth) {
        this.idPerson = idPerson;
        this.fio = fio;
        this.yearOfBirth = yearOfBirth;
    }

    public Person(LocalDate yearOfBirth, String fio) {
        this.yearOfBirth = yearOfBirth;
        this.fio = fio;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Person() {
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public LocalDate getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(LocalDate yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + idPerson +
                ", fio='" + fio + '\'' +
                ", year_of_birth=" + yearOfBirth +
                '}';
    }
}
