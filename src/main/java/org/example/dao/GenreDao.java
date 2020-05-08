package org.example.dao;

import org.example.entities.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.function.Consumer;

public class GenreDao {
    private EntityManager entityManager;

    public void addGenre(Genre genre) {
        getTransaction(entityManager1 -> entityManager1.persist(genre));
    }

    public void updateGenre(Genre genre, String newGenre) {
        genre.setName(newGenre);
        getTransaction(entityManager1 -> entityManager1.merge(genre));
    }

    public void removeGenre(Genre genre) {
        getTransaction(entityManager1 -> entityManager1.remove(entityManager1.contains(genre) ? genre : entityManager1.merge(genre)));
    }

    public void getGenreId(int id) {
        getTransaction(entityManager1 -> entityManager1.find(Genre.class, id));
    }

    public List<Genre> getListGenres() {
        entityManager = Persistence.createEntityManagerFactory("entityManager").createEntityManager();
        List<Genre> genres = entityManager.createNamedQuery("Genre").getResultList();
        entityManager.close();
        return genres;
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
