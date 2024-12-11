package site.javadev.dao; //класс временно имитирует базу данных - кидаем людей в лист,
// а потом сделаем его посредником настоящей базы данных

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import site.javadev.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //BeanPropertyRowMapper
    public List<Person> getAllPeople() {
        return jdbcTemplate.query("SELECT * FROM person",
                new BeanPropertyRowMapper<Person>(Person.class));
    }


    public Person getPersonById(Long id) {
        Person person = jdbcTemplate
                .queryForObject("SELECT * FROM person WHERE id = ?",
                        new BeanPropertyRowMapper<Person>(Person.class), id);
    return person;
    }

    public void save(Person person) {

        jdbcTemplate.update("INSERT INTO person (name, age, email) VALUES ( ?, ?, ?)", person.getName(), person.getAge(), person.getEmail());
    }

    public void update(Person personFromForm, Long id) {
       jdbcTemplate.update("UPDATE person SET name = ?, age = ?, email = ? WHERE id = ?",
               personFromForm.getName(), personFromForm.getAge(), personFromForm.getEmail(), id);
    }




    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }
}
