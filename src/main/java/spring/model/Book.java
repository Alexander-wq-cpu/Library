package spring.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int book_id;
    private String owner_name;

    @NotEmpty(message = "У книги должно быть название")
    @Size(min = 1, max=100, message = "название должно быть от 1 до 100 символов")
    private String title;

    @NotEmpty(message = "Надо указать автора")
    @Size(min=2, max = 100, message = "фио должно быть от 2 до 100 символов")
    private String author;

    @Max(value=2025, message = "год выхода книги должен быть меньше 2025")
    private int release_year;

    public Book() {
    }

    public Book(int book_id, String owner_name, String title, String author, int release_year) {
        this.book_id = book_id;
        this.owner_name = owner_name;
        this.title = title;
        this.author = author;
        this.release_year = release_year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

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

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }
}
