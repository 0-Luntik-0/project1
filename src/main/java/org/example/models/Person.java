package org.example.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class Person {

    private int idPerson;
    @NotEmpty(message = "Введите фио")
    @Size(min = 3,max = 100,message = "ФИО должен быть в диапозоне от 3 до 100")
    private String fio;
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
