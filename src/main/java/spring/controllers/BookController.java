package spring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.dao.BookDAO;
import spring.dao.PersonDAO;
import spring.model.Book;
import spring.model.Person;
import spring.services.BookService;
import spring.services.PeopleService;

@Controller
@RequestMapping("/books")
public class BookController {
   private final BookService bookService;
   private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String getBooks(Model model,
                           @RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "books_per_page",required = false) Integer booksPerPage,
                           @RequestParam(value = "sort_by_year",required = false) Boolean sortByYear) {

        if(sortByYear !=null && sortByYear && booksPerPage != null && page != null) {
            model.addAttribute("books",bookService.findAllSortByYear(page, booksPerPage));
        }
        else if (booksPerPage != null && page != null) {
            model.addAttribute("books",bookService.findAll(page, booksPerPage));
        }
        else if(sortByYear !=null) {
            model.addAttribute("books",bookService.findAllSortByYear());
        }
        else {
            model.addAttribute("books",bookService.findAll());
        }

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

        //bookDAO.addBook(book);
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") int id, Model model){
//        model.addAttribute("book",bookDAO.getBook(id));
//        model.addAttribute("people",personDAO.getPeople());
        model.addAttribute("book",bookService.findById(id));
        model.addAttribute("people",peopleService.findAll());
        model.addAttribute("emptyPerson",new Person());
        return "booksView/bookPage";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
//        bookDAO.deleteBook(id);
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
       // model.addAttribute("book",bookDAO.getBook(id));
        model.addAttribute("book",bookService.findById(id));
        return "booksView/editBook";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id,
                             @ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "booksView/editBook";

        //bookDAO.updateBook(id,book);
        bookService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/assignBook/{id}")
    public String assignBook(@PathVariable("id") int bookId,@ModelAttribute("person") Person person){
        //bookDAO.assignBook(id,book.getOwner_name());
        bookService.assignBookToUser(bookId,person.getId());
        return "redirect:/books/{id}";
    }

    @PatchMapping("/freeOwner/{id}")
    public String freeOwner(@PathVariable("id") int id){
      //  bookDAO.freeOwner(id);
        bookService.unassignBookFromUser(id);
        return "redirect:/books/{id}";
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam(value = "title",required = false)String title,Model model){
        //Если title != null найти книгу в названии которой есть что-то из title
        //вернуть её сюда и положить в модель
        //в bookSearch отобразить её
        if (title != null) {
            model.addAttribute("book",bookService.findByTitleStartingWith(title));
        }

        return "booksView/bookSearch";
    }
}
