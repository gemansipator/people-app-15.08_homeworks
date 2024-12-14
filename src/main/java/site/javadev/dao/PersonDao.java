package site.javadev.dao; //класс временно имитирует базу данных - кидаем людей в лист,
// а потом сделаем его посредником настоящей базы данных

import org.springframework.stereotype.Controller;
import site.javadev.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonDao {
    private static Long NEXT_ID = 0L;

    private final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final String USER = "postgres";
    private final String PASSWORD = "postgres";
    private Connection connection;

    {
        try {
            Class.forName("org.postgresql.Driver");  //загружаем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);//создаем соединение через DriverManager
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<Person> getAllPeople() {
        List<Person> allPeople = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();//Получаем состояние базы данных из нашего подключения к БД
            String sql = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(sql);  //ResultSet - интерфейс обертки данных от sql подобная List
            while (resultSet.next()) {    //.next() возвращает true, если есть следующая строка
                Person person = new Person();
                person.setId(resultSet.getLong("id")); //достается всё по колонкам
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
                allPeople.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPeople;
    }


    public Person getPersonById(Long id) {
        Person person = null;
        try {
            Statement statement = connection.createStatement();//Получаем состояние базы данных из нашего подключения к БД
            String sql = "SELECT * FROM person WHERE id = "+ id;
            ResultSet resultSet = statement.executeQuery(sql);  //ResultSet - интерфейс обертки данных от sql подобная List

            while (resultSet.next()) {    //.next() возвращает true, если есть следующая строка
                person = new Person();
                person.setId(resultSet.getLong("id")); //достается всё по колонкам
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void save(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO person (id, name, age, email) VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, NEXT_ID);
            preparedStatement.setString(2, person.getName());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.executeUpdate();
            NEXT_ID++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Person personFromForm, Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE person SET name = ?, age = ?, email = ? WHERE id = ?");
            preparedStatement.setString(1, personFromForm.getName());
            preparedStatement.setInt(2, personFromForm.getAge());
            preparedStatement.setString(3, personFromForm.getEmail());
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();   //не возвращаем, а просто исполняем
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate(); //не возвращаем, а просто исполняем
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
