package org.example.dao;

import org.example.entities.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.function.Consumer;

public class BookDao {
    private EntityManager entityManager;

    public void addBook(Book book) {
        getTransaction(entityManager1 -> entityManager1.persist(book));
    }

    public void updateBook(Book book, String newBook) {
        book.setTitle(newBook);
        getTransaction(entityManager1 -> entityManager1.merge(book));
    }

    public void removeBook(Book book) {
        getTransaction(entityManager1 -> entityManager1.remove(entityManager1.contains(book) ? book : entityManager1.merge(book)));
    }

    public void getBookId(int id) {
        getTransaction(entityManager1 -> entityManager1.find(Book.class, id));
    }

    public List<Book> getListBooks() {
        entityManager = Persistence.createEntityManagerFactory("entityManager").createEntityManager();
        List<Book> books = entityManager.createNamedQuery("Book").getResultList();
        entityManager.close();
        return books;
    }

    private void getTransaction(Consumer<EntityManager> consumer) {
        entityManager = Persistence.createEntityManagerFactory("entityManager").createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            consumer.accept(entityManager);
            if (transaction.isActive()) {
                transaction.commit();
            }
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
