package org.example.controllers;


import jakarta.validation.Valid;
import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.example.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    private final PersonDAO personDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String mainMenu(Model model) {
        model.addAttribute("main", "Главная страница");

        return "mainMenu";
    }

    @GetMapping("/people")
    public String people(Model model) {
        model.addAttribute("people", personDAO.people());
        return "people/list";
    }

    @GetMapping("/people/{id}")
    public String person(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.personInfo(id));
        return "people/info";
    }

    @GetMapping("/people/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping("/people")
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);

        return "redirect:/people";
    }

    @GetMapping("/people/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.personInfo(id));
        model.addAttribute("id", id);
        return "people/edit";
    }


    @PatchMapping("/people/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult, Model model) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("person", person);
            model.addAttribute("id", id);
            return "people/edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }


}

