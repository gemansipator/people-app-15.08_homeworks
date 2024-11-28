package site.javadev.controllers;


//1 человек - Person     много людей - People

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        // Код который достает людей из БД и загрузит их м модель (буфер обмена)
        List<Person> allPeople = personDao.getAllPeople();
        model.addAttribute("keyOfAllPeople", allPeople);
        return "view-with-all-people";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") Long id, Model model) {

        Person personById = personDao.getPersonById(id);
        model.addAttribute("keyOfPersonById", personById);

        return "view-with-person-by-id";
    }

    @GetMapping("/create")   // /people/create мы выводим шаблон создания человека
    public String giveToUserPageToCreateNewPerson(Model model) {

        model.addAttribute("keyOfNewPerson", new Person());  // создаём нового человека и он попадает
        // в модель в буфер

        return "view-to-create-new-person";
    }

    @PostMapping()                    // ловим созданного человека через @ModelAttribute по ключу("keyOfNewPerson")
    public String createPerson(@ModelAttribute("keyOfNewPerson") Person person) {

        personDao.save(person);   // сохраняем в БД
        return "redirect:/people"; // перенаправляем на страницу всех людей
    }





}

