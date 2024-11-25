package site.javadev.dao; //класс временно имитирует базу данных - кидаем людей в лист,
// а потом сделаем его посредником настоящей базы данных

import lombok.Data;
import lombok.NoArgsConstructor;
import site.javadev.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDao {

//    public List<Person> allPeople;
    public List<Person> allPeople;

    {
        allPeople = new ArrayList<Person>(); //создается пустой список во время иннициализации класса
        // и ниже добавляем изначальный список людей

        allPeople.add(new Person(1L, "Georgy"));
        allPeople.add(new Person(2L, "Bilal"));
        allPeople.add(new Person(3L, "Alex"));
        allPeople.add(new Person(4L, "Nikolay L"));
        allPeople.add(new Person(5L, "Nikolay P"));
    }

    public List<Person> getAllPersons() {
        return allPeople;  //возвращает текущий список после поднятия
    }


}
