package spring.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.model.Book;
import spring.repositories.BookRepository;
import spring.repositories.PeopleRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    public final BookRepository bookRepository;
    public final PeopleRepository peopleRepository;

    @Autowired
    public BookService(BookRepository bookRepository, PeopleRepository peopleRepository) {
        this.bookRepository = bookRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findAll(int page, int books_per_page) {
       return bookRepository.findAll(PageRequest.of(page, books_per_page)).getContent();
    }

    public List<Book> findAllSortByYear() {
        return bookRepository.findAll(Sort.by("year"));
    }

    public List<Book> findAllSortByYear(int page, int books_per_page) {
        return bookRepository.findAll(PageRequest.of(page, books_per_page,Sort.by("year"))).getContent();
    }

    public Book findByTitleStartingWith(String title){
      return bookRepository.findByTitleStartingWith(title);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book findById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void update(int id,Book updatedBook) {
        Book bookToBeUpdated = bookRepository.findById(id).get();
        updatedBook.setBook_id(id);
        updatedBook.setOwner(bookToBeUpdated.getOwner());
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void assignBookToUser(int bookId, int personId) {
        Book bookToAssign = bookRepository.findById(bookId).orElse(null);
        if (bookToAssign != null) {
            bookToAssign.setTakenAtTime(LocalDateTime.now());
            bookToAssign.setOwner(peopleRepository.findById(personId).orElse(null));
        }
    }

    @Transactional
    public void unassignBookFromUser(int bookId) {
       bookRepository.findById(bookId).ifPresent(
               book -> {
                   book.setOwner(null);
                   book.setTakenAtTime(null);
               });
    }
}
