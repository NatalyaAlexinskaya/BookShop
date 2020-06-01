package org.example.dao;

import org.example.entities.Genre;
import org.example.repository.GenreDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.function.Consumer;

public class GenreDaoImpl implements GenreDao {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public GenreDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addGenre(Genre genre) {
        getTransaction(entityManager1 -> entityManager1.persist(genre));
    }

    @Override
    public void updateGenre(Genre genre) {
        getTransaction(entityManager1 -> entityManager1.merge(genre));
    }

    @Override
    public void removeGenre(Genre genre) {
        getTransaction(entityManager1 -> entityManager1.remove(entityManager1.contains(genre) ? genre : entityManager1.merge(genre)));
    }

    @Override
    public Genre getGenreId(int id) {
        entityManager = entityManagerFactory.createEntityManager();
        Genre genre = entityManager.createNamedQuery("Genre.ID", Genre.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.close();
        return genre;
    }

    @Override
    public List<Genre> getListGenres() {
        entityManager = entityManagerFactory.createEntityManager();
        List<Genre> genres = entityManager.createNamedQuery("Genre").getResultList();
        entityManager.close();
        return genres;
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
