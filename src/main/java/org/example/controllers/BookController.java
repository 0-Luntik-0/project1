package org.example.controllers;

import jakarta.validation.Valid;

import org.example.models.Book;
import org.example.services.BookService;
import org.example.services.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;


    private final PeopleService peopleService;
    public BookController(BookService bookInfo, BookService bookService,PeopleService peopleService) {
        this.bookService = bookService;

        this.peopleService = peopleService;
    }


    @GetMapping()
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    @GetMapping("/{id}")
    public String book(@PathVariable("id") int id, Model model) {

        model.addAttribute("book", bookService.findOne(id));
        model.addAttribute("people", peopleService.findAll()); // Передаем список людей
        return "books/info";
    }

    @PatchMapping("/{id}/add")
    public String assignBook(@PathVariable("id") int bookId, @RequestParam("personId") int personId) {
        bookService.assignToPerson(bookId, personId);
        return "redirect:/books";
    }


    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.findOne(id));
        model.addAttribute("id", id);
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/remove")
    public String removeBook(@PathVariable("id") int bookId) {
        bookService.removePersonFromBook(bookId);
        return "redirect:/books";

    }


}