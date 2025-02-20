package org.example.services;

import org.example.models.Person;
import org.example.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PersonRepository personRepository;

    @Autowired
    public PeopleService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public List<Person> findAll() {
        return personRepository.findAllBy();
    }

    @Transactional
    public Person findOne(int id) {
        return personRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void update(int id, Person newPerson) {
        newPerson.setIdPerson(id);
        personRepository.save(newPerson);

    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);

    }


}
