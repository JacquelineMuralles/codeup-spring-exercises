package com.example.springblogapp.controller;

import com.example.springblogapp.model.Book;
import com.example.springblogapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    //Dependency Injection (brings repo over and acts like the dao)
    private BookRepository bookRepo;

    public BookController(BookRepository bookRepo){
        this.bookRepo = bookRepo;
    }

    //grabbing all books
    @GetMapping("/books")
    @ResponseBody
    public String getBooks() {
        String books = "<ul>Books";
        for (Book book : this.bookRepo.findAll()) {
            books += "<li>" + book.getTitle() + " by " + book.getAuthor() + "</li>";
        }
        books += "</ul>";
        return books;
    }

    //finding books by the year published
    @GetMapping("/books/{year}")
    @ResponseBody
    public String getBooks(@PathVariable int year){
        String books = "<ul>Books published in " + year;
        for (Book book : this.bookRepo.findByPubYear(year)) {
            books += "<li>" + book.getTitle() + " by " + book.getAuthor() + "</li>";
        }
        books += "</ul";
        return books;
    }

    //brings up the form to create a book
    @GetMapping("/books/create")
    public String sendBookForm(){
        return "/books/create.html";
    }

    @PostMapping("/books/create")
    @ResponseBody
    public String newBook(
            @RequestParam(name = "title") String titleParam,
            @RequestParam(name = "author") String authorParam,
            @RequestParam(name = "year") int published
    ) {
        Book book = new Book();
        book.setTitle(titleParam);
        book.setAuthor(authorParam);
        book.setPubYear(published);
        this.bookRepo.save(book);
        return "New Book Created";
    }

}
