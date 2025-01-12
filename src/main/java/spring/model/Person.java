package spring.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int person_id;

    @NotEmpty(message = "Поле имя не должно быть пустым")
    @Size(min = 1, max=30, message = "Имя должно иметь длину от 1 до 30 символов")
    private String name;

    @Min(value = 1900, message = "Год рождения должен быть не ранее 1900")
    @Max(value = 2025, message = "Год рождения не должен быть больше 2025")
    private int birthdate;

    public Person() {
    }

    public Person(int person_id, String name, int birthdate) {
        this.person_id = person_id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
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
}
