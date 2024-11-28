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
    private static Long NEXT_ID = 0L;

    private List<Person> allPeople;

    {
        allPeople = new ArrayList<>(); //создается пустой список во время иннициализации класса
        // и ниже добавляем изначальный список людей

        allPeople.add(new Person(++NEXT_ID, "Georgy"));
        allPeople.add(new Person(++NEXT_ID, "Bilal"));
        allPeople.add(new Person(++NEXT_ID, "Alex"));
        allPeople.add(new Person(++NEXT_ID, "Nikolay L"));
        allPeople.add(new Person(++NEXT_ID, "Nikolay P"));

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

    public void save(Person person) {
        person.setId(++NEXT_ID);  //обновляеим id
        allPeople.add(person);    //добавляем созданого человека в список
    }
}
