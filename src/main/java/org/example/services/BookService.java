package org.example.services;

import org.example.models.Book;
import org.example.models.Person;
import org.example.repositories.BookRepository;
import org.example.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    public BookService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book findOne(int id) {
        return bookRepository.findByIdBook(id);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updateBook) {

        updateBook.setIdBook(id);
        bookRepository.save(updateBook);

    }

    @Transactional
    public List<Book> getBooksByPerson(int personId) {
        Person person = personRepository.findById(personId).orElse(null);
        return (person != null) ? bookRepository.findByPerson(person) : Collections.emptyList();
    }



    @Transactional
    public void assignToPerson(int bookId, int personId) {
        bookRepository.findById(bookId).ifPresent(book ->
                personRepository.findById(personId).ifPresent(book::setPerson)
        );
    }


    @Transactional
    public void removePersonFromBook(int bookId) {
        bookRepository.findById(bookId).ifPresent(book -> book.setPerson(null));
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);

    }





}
