package ru.sasha.spring.controllers;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sasha.spring.controllers.data.BooksControllerHelper;
import ru.sasha.spring.dao.BookDAO;
import ru.sasha.spring.dao.PersonDAO;
import ru.sasha.spring.models.Book;
import ru.sasha.spring.models.Person;

@Controller
@RequestMapping("books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute(BooksControllerHelper.book.getTitle(), bookDAO.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book) {
        bookDAO.save(book);
        return BooksControllerHelper.redirect.getTitle();
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model,
                       @ModelAttribute("person") Person person) {

        model.addAttribute(BooksControllerHelper.book.getTitle(), bookDAO.show(id));

        Optional<Person> bookOwner = bookDAO.getBookOwner(id);

        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("notowner", personDAO.index());
        }
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,
                       @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book,
                         @PathVariable("id") int id) {
        bookDAO.update(id, book);
        return BooksControllerHelper.redirect.getTitle();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return BooksControllerHelper.redirect.getTitle();
    }

    @PatchMapping("/{id}/release")
    public String release(@ModelAttribute("book") Book book,
                          @PathVariable("id") int id) {
        bookDAO.release(id);
        return BooksControllerHelper.redirect.getTitle() + "/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {

        bookDAO.assign(id, person);
        return BooksControllerHelper.redirect.getTitle() + "/" + id;
    }
}
