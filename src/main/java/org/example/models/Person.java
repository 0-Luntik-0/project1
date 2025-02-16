package org.example.models;

import java.time.LocalDate;

public class Person {
    private int id;
    private String fio;
    private LocalDate year_of_birth;

    public Person(int id, String fio, LocalDate year_of_birth) {
        this.id = id;
        this.fio = fio;
        this.year_of_birth = year_of_birth;
    }

    public Person(LocalDate year_of_birth, String fio) {
        this.year_of_birth = year_of_birth;
        this.fio = fio;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public LocalDate getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(LocalDate year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", year_of_birth=" + year_of_birth +
                '}';
    }
}
