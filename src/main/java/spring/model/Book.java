package spring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int book_id;

    /*@Column(name = "owner_name")
    private String owner_name;*/

    @Column(name = "title")
    @NotEmpty(message = "У книги должно быть название")
    @Size(min = 1, max=100, message = "название должно быть от 1 до 100 символов")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "Надо указать автора")
    @Size(min=2, max = 100, message = "фио должно быть от 2 до 100 символов")
    private String author;

    @Column(name = "release_year")
    @Max(value=2025, message = "год выхода книги должен быть меньше 2025")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id",referencedColumnName = "id")
    private Person owner;

    @Column(name = "taken_at_time")
    private LocalDateTime takenAtTime;

    @Transient
    private boolean overdue = false;

    public Book() {
    }

    public Book(int book_id, String title, String author, int year,LocalDateTime takenAtTime) {
        this.book_id = book_id;
       // this.owner_name = owner_name;
        this.title = title;
        this.author = author;
        this.year = year;
        this.takenAtTime = takenAtTime;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

   /* public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person person) {
        this.owner = person;
    }

    public LocalDateTime getTakenAtTime() {
        return takenAtTime;
    }

    public void setTakenAtTime(LocalDateTime takenAtTime) {
        this.takenAtTime = takenAtTime;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }
}
