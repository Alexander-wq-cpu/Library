package spring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.dao.BookDAO;
import spring.dao.PersonDAO;
import spring.model.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO,BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO=bookDAO;
    }

    @GetMapping()
    public String hello(Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "people";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "newPerson";
    }

    @PostMapping()
    public String addPerson(@ModelAttribute("person") @Valid Person person,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "newPerson";

        personDAO.addPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "editPerson";
    }

    @PatchMapping({"/{id}"})
    public String updatePerson(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult,
                               @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "editPerson";

        personDAO.updatePerson(person, id);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        model.addAttribute("books",bookDAO.getPersonsBooks(id));
        return "showPerson";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }
}
