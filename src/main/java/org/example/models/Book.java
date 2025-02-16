package org.example.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class Book {
    private int id;

    @NotEmpty(message = "Введите имя")
    @Size(min = 2, max = 30, message = "Имя должно быть в диапазоне от 2 до 30")
    private String name;
    @NotEmpty(message = "Введите автора")
    @Size(min = 2, max = 30, message = "Имя автора должно быть в диапазоне от 2 до 30")
    private String author;
    @PastOrPresent(message = "Книга не может быть из будующего")
    private LocalDate year; // год выпуска книги

    public Book(int id, String name, String author, LocalDate year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book(String name, String author, LocalDate year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
