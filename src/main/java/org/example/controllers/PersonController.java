package org.example.controllers;


import jakarta.validation.Valid;
import org.example.models.Book;
import org.example.models.Person;
import org.example.repositories.PersonRepository;
import org.example.services.BookService;
import org.example.services.PeopleService;
import org.example.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PersonController {


    private final PersonValidator personValidator;
    private final PeopleService peopleService;
    private final BookService bookService;
    private final PersonRepository personRepository;


    @Autowired
    public PersonController(PersonValidator personValidator, PeopleService peopleService, BookService bookService, PersonRepository personRepository) {

        this.personValidator = personValidator;
        this.peopleService = peopleService;
        this.bookService = bookService;

        this.personRepository = personRepository;
    }

    @GetMapping() // Главное меню
    public String mainMenu(Model model) {
        model.addAttribute("main", "Главная страница");

        return "mainMenu";
    }

    @GetMapping("/people")
    public String listPeople(@RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "10") int size,
                             Model model) {
        Page<Person> peoplePage = personRepository.findAll(PageRequest.of(page, size, Sort.by("yearOfBirth")));
        model.addAttribute("people", peoplePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", peoplePage.getTotalPages());
        return "people/list";
    }


    @GetMapping("/people/{id}") // личная информация со списком книг
    public String personInfo(@PathVariable("id") int id, Model model) {
        Person person = peopleService.findOne(id);
        List<Book> books = bookService.getBooksByPerson(id);

        model.addAttribute("person", person);
        model.addAttribute("books", books);

        return "people/info";
    }


    @GetMapping("/people/new") // сраница создания пользователя
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping("/people") // сохранение пользователя
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.save(person);

        return "redirect:/people";
    }

    @GetMapping("/people/{id}/edit") // страница редактирования пользователя
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));

        model.addAttribute("id", id);
        return "people/edit";
    }


    @PatchMapping("/people/{id}") // сохранение изменений
    public String update(@PathVariable("id") int id, @ModelAttribute("person")
    @Valid Person person, BindingResult bindingResult, Model model) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("person", person);
            model.addAttribute("id", id);
            return "people/edit";
        }
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("people/{id}") // удаление пользователя
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }


}

