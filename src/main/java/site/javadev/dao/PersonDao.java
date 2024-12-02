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

        allPeople.add(new Person(++NEXT_ID, "Georgy", 25, "georgy@example.com"));
        allPeople.add(new Person(++NEXT_ID, "Bilal", 19, "bilal@example.com"));
        allPeople.add(new Person(++NEXT_ID, "Alex", 41, "alex@example.com"));
        allPeople.add(new Person(++NEXT_ID, "Nikolay L", 42, "nikolay.l@example.com"));
        allPeople.add(new Person(++NEXT_ID, "Nikolay P", 34, "nikolay.p@example.com"));

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

    public void update(Person personFromForm, Long id) {
        Person person = getPersonById(id);   // находим человека по ID
        if (person != null) {
            person.setName(personFromForm.getName());
            person.setAge(personFromForm.getAge());
            person.setEmail(personFromForm.getEmail());
        }
    }

}
