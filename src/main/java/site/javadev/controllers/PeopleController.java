package site.javadev.controllers;


//1 человек - Person     много людей - People

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.javadev.dao.PersonDao;
import site.javadev.model.Person;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDao personDao;

    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String getAllPeople(Model model) {
        List<Person> allPeople = personDao.getAllPeople(); // теперь всё корректно
        model.addAttribute("keyOfAllPeople", allPeople);
        return "view-with-all-people";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") Long id, Model model) {

        Person personById = personDao.getPersonById(id);
        model.addAttribute("keyOfPersonById", personById);

        return "view-with-person-by-id";
    }

    @GetMapping("/create")
    public String giveToUserPageToCreateNewPerson(Model model) {

        model.addAttribute("keyOfNewPerson", new Person());

        return "view-to-create-new-person";
    }



}

