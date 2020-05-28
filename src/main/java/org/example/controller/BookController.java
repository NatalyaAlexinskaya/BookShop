package org.example.controller;

import org.example.entities.Book;
import org.example.repository.BookDao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "book")
public class BookController {
    private BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping(path = "/all")
    public List<Book> getBooks() {
        return bookDao.getListBooks();
    }

    @GetMapping(path = "/{id}")
    public Book getById(@PathVariable(name = "id") Integer id) {
        return bookDao.getBookId(id);
    }

    @PostMapping(path = "/add")
    public void addBook(@RequestBody Book book) {
        if (book != null) {
            bookDao.addBook(book);
        }
    }

    @PutMapping(path = "/update")
    public void updateBook(@RequestBody Book book) {
        bookDao.updateBook(book);
    }

    @DeleteMapping(path = "/remove")
    public void removeBook(@RequestBody Book book) {
        bookDao.removeBook(book);
    }
}
