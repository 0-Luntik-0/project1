package org.example.repositories;

import org.example.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findAllBy();

Optional<Person> findByFio(String fio);



}
