package org.example.models;

import jakarta.validation.constraints.*;

public class Book {
    private int idBook;
    private Integer idPerson;
    private String fio; // Добавляем поле для хранения имени владельца книги



    @NotEmpty(message = "Введите имя")
    @Size(min = 2, max = 30, message = "Имя должно быть в диапазоне от 2 до 30")
    private String name;
    @NotEmpty(message = "Введите автора")
    @Size(min = 2, max = 30, message = "Имя автора должно быть в диапазоне от 2 до 30")
    private String author;
    @NotNull(message = "Введите год")
    @Min(value = 800, message = "Введите корректное значение" )
    @Max(value = 2025,message = "Введите корректное значение" )
    private int year; // год выпуска книги

    public Book(int idBook, String name, String author, int year,int idPerson) {
        this.idBook = idBook;
        this.name = name;
        this.author = author;
        this.year = year;
        this.idPerson = idPerson;
    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public Book() {
    }

    public int getIdBook() {
        return idBook;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
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
