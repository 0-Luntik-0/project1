package org.example.controllers;


import jakarta.validation.Valid;
import org.example.dao.BookDAO;
import org.example.dao.PersonDAO;
import org.example.models.Book;
import org.example.models.Person;
import org.example.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PersonController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonDAO personDAO, BookDAO bookDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.personValidator = personValidator;
    }

    @GetMapping() // Главное меню
    public String mainMenu(Model model) {
        model.addAttribute("main", "Главная страница");

        return "mainMenu";
    }

    @GetMapping("/people") // список пользователей
    public String people(Model model) {
        model.addAttribute("people", personDAO.people());
        return "people/list";
    }

    @GetMapping("/people/{id}") // личная информация со списком книг
    public String personInfo(@PathVariable("id") int id, Model model) {
        Person person = personDAO.personInfo(id);
        List<Book> books = bookDAO.getBooksByPerson(id);

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

        personDAO.save(person);

        return "redirect:/people";
    }

    @GetMapping("/people/{id}/edit") // страница редактирования пользователя
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.personInfo(id));
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
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("people/{id}") // удаление пользователя
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }







}

