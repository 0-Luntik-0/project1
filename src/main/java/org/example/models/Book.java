package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private int idBook;

    @Column(name = "name")
    @NotEmpty(message = "Введите имя")
    @Size(min = 2, max = 200, message = "Имя должно быть в диапазоне от 2 до 200")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "Введите автора")
    @Size(min = 2, max = 100, message = "Имя автора должно быть в диапазоне от 2 до 100")
    private String author;

    @Column(name = "year")
    @NotNull(message = "Введите год")
    @Min(value = 800, message = "Введите корректное значение")
    @Max(value = 2025, message = "Введите корректное значение")
    private int year; // год выпуска книги

    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person person;


    public Book(int idBook, String name, String author, int year) {
        this.idBook = idBook;
        this.name = name;
        this.author = author;
        this.year = year;

    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;

    }


    public Book() {
    }

    public int getIdBook() {
        return idBook;
    }



    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + idBook +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
