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

        allPeople.add(new Person(++NEXT_ID, "Georgy", 30, "georgy@example.com"));
        allPeople.add(new Person(++NEXT_ID, "Bilal", 25, "bilal@example.com"));
        allPeople.add(new Person(++NEXT_ID, "Alex", 35, "alex@example.com"));
        allPeople.add(new Person(++NEXT_ID, "Nikolay L", 40, "nikolay.l@example.com"));
        allPeople.add(new Person(++NEXT_ID, "Nikolay P", 28, "nikolay.p@example.com"));

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
