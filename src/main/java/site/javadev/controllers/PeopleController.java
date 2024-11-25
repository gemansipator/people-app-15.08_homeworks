package site.javadev.controllers;


//1 человек - Person     много людей - People

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.javadev.dao.PersonDao;
import site.javadev.model.Person;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDao personDao; //берем в работу посредника базы данных с эмуляцией базы
    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()      //если без параметров то просто возвращаем всё (всех людей)
    public String getAllPeople(Model model) {
        // Код, который достанет нам людей из БД и загрузит их в модель (буфер обмена)

        List<Person> allPeople = personDao.getAllPeople();
        model.addAttribute("keyOfallPeople", allPeople);



        return "view-with-all-people";
    }


}
