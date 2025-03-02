package org.example.repositories;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {



    Book findByIdBook(int idBook);

    List<Book> findByPerson(Person person);

}
