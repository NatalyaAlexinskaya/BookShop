package org.example.dao;

import org.example.entities.Book;
import org.example.repository.BookDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.function.Consumer;

public class BookDaoImpl implements BookDao {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public BookDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addBook(Book book) {
        getTransaction(entityManager1 -> entityManager1.persist(book));
    }

    @Override
    public void updateBook(Book book) {
        getTransaction(entityManager1 -> entityManager1.merge(book));
    }

    @Override
    public void removeBook(Book book) {
        getTransaction(entityManager1 -> entityManager1.remove(entityManager1.contains(book) ? book : entityManager1.merge(book)));
    }

    @Override
    public Book getBookId(Integer id) {
        entityManager = entityManagerFactory.createEntityManager();
        Book book = entityManager.createNamedQuery("Book.ID", Book.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.close();
        return book;
    }

    @Override
    public List<Book> getListBooks() {
        entityManager = entityManagerFactory.createEntityManager();
        List<Book> books = entityManager.createNamedQuery("Book").getResultList();
        entityManager.close();
        return books;
    }

    private void getTransaction(Consumer<EntityManager> consumer) {
        entityManager = entityManagerFactory.createEntityManager();
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
