package org.example.dao;

import org.example.Book;

import javax.persistence.EntityManager;
import java.util.List;

public class BookDao {
    private EntityManager entityManager;

    public BookDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addBook(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

    public void updateBook(Book book, String newBook) {
        entityManager.getTransaction().begin();
        book.setTitle(newBook);
        entityManager.merge(book);
        entityManager.getTransaction().commit();
    }

    public void removeBook(int id) {
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
        entityManager.getTransaction().commit();
    }

    public Book getBookId(int id) {
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, id);
        entityManager.getTransaction().commit();

        return book;
    }

    public List<Book> getListBooks() {
        return entityManager.createNamedQuery("Book").getResultList();
    }
}
