package org.example;

import org.example.dao.BookDao;
import org.example.entities.Book;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestBd {
    @Test
    public void createBook() {
        BookDao bookDao = new BookDao();
        Book book = new Book();
        book.setTitle("Тайная комната");
        book.setAuthor("Джоан Роулинг");
        bookDao.addBook(book);
    }

    @Test
    public void removeBook() {
        BookDao bookDao = new BookDao();
        Book book = new Book();
        book.setTitle("Тайная комната");
        book.setAuthor("Джоан Роулинг");
        bookDao.addBook(book);

        bookDao.removeBook(book);
    }

}
