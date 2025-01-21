package spring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Поле имя не должно быть пустым")
    @Size(min = 1, max=30, message = "Имя должно иметь длину от 1 до 30 символов")
    private String name;

    @Column(name = "birthdate")
    @Min(value = 1900, message = "Год рождения должен быть не ранее 1900")
    @Max(value = 2025, message = "Год рождения не должен быть больше 2025")
    private int birthdate;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {
    }

    public Person(int id, String name, int birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int person_id) {
        this.id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(int birthdate) {
        this.birthdate = birthdate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
