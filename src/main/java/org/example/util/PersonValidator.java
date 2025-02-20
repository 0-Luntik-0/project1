package org.example.util;

import org.example.models.Person;
import org.example.repositories.PersonRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PersonRepository personRepository;

    public PersonValidator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;


        Optional<Person> existingPerson = personRepository.findByFio(person.getFio());

        if (existingPerson.isPresent()) {
            errors.rejectValue("fio", "", "Человек с данным ФИО уже существует");
        }
    }
}
