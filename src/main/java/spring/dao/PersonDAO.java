package spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.model.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople(){
        return jdbcTemplate.query("select * from person order by person_id", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPerson(int id) {
        return jdbcTemplate.queryForObject("select * from person where person_id=?",
                new BeanPropertyRowMapper<>(Person.class), id);
    }

    public void addPerson(Person person){
        jdbcTemplate.update("insert into person(name, birthdate) values(?,?)", person.getName(), person.getBirthdate());
    }


    public void updatePerson(Person person,int id){
        jdbcTemplate.update("update person set name=?, birthdate=? where person_id=?",
                person.getName(),person.getBirthdate(),id);
    }

    public void deletePerson(int id){
        jdbcTemplate.update("delete from person where person_id=?", id);
    }
}
