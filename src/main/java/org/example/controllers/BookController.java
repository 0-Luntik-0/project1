package org.example.controllers;

import org.example.dao.BookDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;

    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    @GetMapping()
    public String list(Model model) {
        model.addAttribute("books", bookDAO.list());
        return "books/list";
    }
}