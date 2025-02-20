package org.example.repositories;

import org.example.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findAllBy();

    Page<Person> findAll(Pageable pageable);

    List<Person> findAll(Sort var1);

    Optional<Person> findByFio(String fio);


}
