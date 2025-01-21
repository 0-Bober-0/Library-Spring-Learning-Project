package ru.sasha.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sasha.spring.controllers.data.PeopleControllerHelper;
import ru.sasha.spring.dao.BookDAO;
import ru.sasha.spring.dao.PersonDAO;
import ru.sasha.spring.models.Person;

@Controller
@RequestMapping("people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    public PeopleController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute(PeopleControllerHelper.people.getTitle(), personDAO.index());
        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return PeopleControllerHelper.redirect.getTitle();
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,
                       @PathVariable("id") int id,
                       @ModelAttribute("person") Person person) {
        model.addAttribute((PeopleControllerHelper.people.getTitle()), personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {

        personDAO.update(id, person);
        return PeopleControllerHelper.redirect.getTitle();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return PeopleControllerHelper.redirect.getTitle();
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model) {

        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", bookDAO.getBooks(id));
        return "people/show";
    }
}
