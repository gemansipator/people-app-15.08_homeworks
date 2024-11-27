package site.javadev.dao; //класс временно имитирует базу данных - кидаем людей в лист,
// а потом сделаем его посредником настоящей базы данных

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import site.javadev.model.Person;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonDao {

    private List<Person> allPeople;

    {
        allPeople = new ArrayList<>(); //создается пустой список во время иннициализации класса
        // и ниже добавляем изначальный список людей

        allPeople.add(new Person(1L, "Georgy"));
        allPeople.add(new Person(2L, "Bilal"));
        allPeople.add(new Person(3L, "Alex"));
        allPeople.add(new Person(4L, "Nikolay L"));
        allPeople.add(new Person(5L, "Nikolay P"));

        // Лог для проверки
        allPeople.forEach(person -> System.out.println("Loaded person: " + person));
    }

    public List<Person> getAllPeople() {
        return allPeople;  //возвращает текущий список после поднятия
    }


    public Person getPersonById(Long id) {

        return allPeople.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElse(null);

    }

}
