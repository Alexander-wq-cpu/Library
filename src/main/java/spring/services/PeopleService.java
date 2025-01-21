package spring.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.model.Book;
import spring.model.Person;
import spring.repositories.PeopleRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    public Person findById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updatePerson(int id,Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    public List<Book> getPersonBooks(int id) {
        Person person = peopleRepository.findById(id).orElse(null);
        if (person == null) {
            return null;
        }
        Hibernate.initialize(person.getBooks());
        List<Book> books = person.getBooks();
        if (books != null) {
            for (Book book : books) {
                if(LocalDateTime.now().getDayOfYear() - book.getTakenAtTime().getDayOfYear() >10)
                    book.setOverdue(true);
            }
        }

        return person.getBooks();
    }

    @Transactional
    public void deletePerson(int id) {
        peopleRepository.deleteById(id);
    }
}
