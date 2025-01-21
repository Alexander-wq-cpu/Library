package spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.model.Book;
import spring.model.Person;


import java.util.List;

@Component
public class BookDAO {
/*
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
       // this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
      // return jdbcTemplate.query("select * from book order by book_id", new BeanPropertyRowMapper<>(Book.class));
        return null;
    }
    public void addBook(Book book) {
//        jdbcTemplate.update("insert into book(title,author,release_year) values(?,?,?)",
//                book.getTitle(),book.getAuthor(),book.getYear());
    }

    public Book getBook(int id) {
      //  return jdbcTemplate.queryForObject("select * from book where book_id=?", new BeanPropertyRowMapper<>(Book.class),id);
        return null;
    }

    public void updateBook(int id,Book book) {
//        jdbcTemplate.update("update book set title=?, author=?, release_year=? where book_id=?",
//                book.getTitle(),book.getAuthor(),book.getYear(),id);
    }

    public void deleteBook(int id) {
      //  jdbcTemplate.update("delete from book where book_id=?",id);
    }

    public List<Book> getPersonsBooks(int id) {
//        Person bookOwner = jdbcTemplate.queryForObject("select * from person where person_id=?",
//                new BeanPropertyRowMapper<>(Person.class),id);
//        return jdbcTemplate.query("select * from book where owner_name=?", new BeanPropertyRowMapper<>(Book.class),
//                bookOwner.getName());
        return null;
    }

    public void assignBook(int book_id, String owner_name) {
      //  jdbcTemplate.update("update book set owner_name=? where book_id=?",owner_name,book_id);
    }

    public void freeOwner(int book_id) {
        //jdbcTemplate.update("update book set owner_name=null where book_id=?",book_id);
    }*/
}
