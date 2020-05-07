package org.example;

import org.example.dao.BookDao;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestBd {
    @Test
    public void createBook() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "entityManager" );
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        BookDao bookDao = new BookDao(entityManager);
        Book book = new Book();
        book.setTitle("Тайная комната");
        book.setAuthor("Джоан Роулинг");
        bookDao.addBook(book);

        entityManagerFactory.close();
        entityManager.close();
    }

    @Test
    public void removeBook() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "entityManager" );
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        BookDao bookDao = new BookDao(entityManager);
        Book book = new Book();
        book.setTitle("Тайная комната");
        book.setAuthor("Джоан Роулинг");
        bookDao.addBook(book);

        bookDao.removeBook(1);

        entityManagerFactory.close();
        entityManager.close();
    }

}
