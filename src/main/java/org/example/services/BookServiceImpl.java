package org.example.services;

import org.example.entities.Book;
import org.example.repository.BookDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public void removeBook(Book book) {
        bookDao.removeBook(book);
    }

    @Override
    public Book getBookId(int id) {
        return bookDao.getBookId(id);
    }

    @Override
    public List<Book> getListBooks() {
        return bookDao.getListBooks();
    }
}
