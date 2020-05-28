package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entities.Book;
import org.example.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "book")
public class BookController {
    private BookService bookService;

    @GetMapping(path = "/all")
    public Iterable<Book> getBooks() {
        return bookService.getListBooks();
    }

    @GetMapping(path = "/{id}")
    public Optional<Book> getById(@PathVariable(name = "id") Integer id) {
        return bookService.getBookId(id);
    }

    @PostMapping(path = "/add")
    public void addBook(@RequestBody Book book) {
        if (book != null) {
            bookService.addBook(book);
        }
    }

    @DeleteMapping(path = "/remove")
    public void removeBook(@RequestBody Book book) {
        bookService.removeBook(book);
    }
}
