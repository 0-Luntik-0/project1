package org.example.controllers;

import org.example.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    private final PersonDAO personDAO;

    @Autowired
    public BookController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String mainMenu(Model model) {
        model.addAttribute("main", "Главная страница");

        return "mainMenu";
    }

    @GetMapping("/people")
    public String people(Model model) {
        model.addAttribute("people", personDAO.people());
        return "people";
    }
}
