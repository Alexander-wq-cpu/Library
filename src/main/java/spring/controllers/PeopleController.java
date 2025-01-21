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
import spring.services.PeopleService;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {

        this.peopleService = peopleService;
    }

    @GetMapping()
    public String hello(Model model) {
        model.addAttribute("people", peopleService.findAll());
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

        peopleService.save(person);

       // personDAO.addPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
      //  model.addAttribute("person", personDAO.getPerson(id));
        model.addAttribute("person", peopleService.findById(id));
        return "editPerson";
    }

    @PatchMapping({"/{id}"})
    public String updatePerson(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult,
                               @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "editPerson";

      //  personDAO.updatePerson(person, id);
        peopleService.updatePerson(id, person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
//        model.addAttribute("person", personDAO.getPerson(id));
//        model.addAttribute("books",bookDAO.getPersonsBooks(id));
        model.addAttribute("person", peopleService.findById(id));
        model.addAttribute("books",peopleService.getPersonBooks(id));
        return "showPerson";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
      //  personDAO.deletePerson(id);
        peopleService.deletePerson(id);
        return "redirect:/people";
    }
}
