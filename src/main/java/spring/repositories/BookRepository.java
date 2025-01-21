package spring.repositories;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitleStartingWith(String title);
}
