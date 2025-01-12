package spring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.dao.BookDAO;
import spring.dao.PersonDAO;
import spring.model.Book;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO){
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getBooks(Model model){
          model.addAttribute("books",bookDAO.getBooks());
        return "booksView/allBooks";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "booksView/newBook";
    }

    @PostMapping()
    public String saveBook(@ModelAttribute("book") @Valid Book book,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "booksView/newBook";

        bookDAO.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book",bookDAO.getBook(id));
        model.addAttribute("people",personDAO.getPeople());
        return "booksView/bookPage";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book",bookDAO.getBook(id));
        return "booksView/editBook";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id,
                             @ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "booksView/editBook";

        bookDAO.updateBook(id,book);
        return "redirect:/books";
    }

    @PatchMapping("/assignBook/{id}")
    public String assignBook(@PathVariable("id") int id,@ModelAttribute("book") Book book){
        bookDAO.assignBook(id,book.getOwner_name());
        return "redirect:/books/{id}";
    }

    @PatchMapping("/freeOwner/{id}")
    public String freeOwner(@PathVariable("id") int id){
        bookDAO.freeOwner(id);
        return "redirect:/books/{id}";
    }
}
