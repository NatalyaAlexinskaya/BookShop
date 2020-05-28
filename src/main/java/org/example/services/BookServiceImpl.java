package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entities.Book;
import org.example.repository.BookDao;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    @Override
    public void addBook(Book book) {
        bookDao.save(book);
    }

    @Override
    public void removeBook(Book book) {
        bookDao.delete(book);
    }

    @Override
    public Optional<Book> getBookId(int id) {
        return bookDao.findById(id);
    }

    @Override
    public Iterable<Book> getListBooks() {
        return bookDao.findAll();
    }
}
