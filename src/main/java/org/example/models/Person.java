package org.example.models;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class Person {
    private int idPerson;


    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-z]+\\s[А-ЯЁA-Z][а-яёa-z]+(?:\\s[А-ЯЁA-Z][а-яёa-z]+)?$",
            message = "Введите минимум имя и фамилию, допускается до 3 слов. Только буквы и пробел😊")
    private String fio;

    @NotNull(message = "Введите год рожедения")
    @PastOrPresent(message = "Вы не можете родиться в будующем")
    private LocalDate yearOfBirth;

    public Person(int idPerson, String fio, LocalDate yearOfBirth) {
        this.idPerson = idPerson;
        this.fio = fio;
        this.yearOfBirth = yearOfBirth;
    }

    public Person(LocalDate yearOfBirth, String fio) {
        this.yearOfBirth = yearOfBirth;
        this.fio = fio;
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
