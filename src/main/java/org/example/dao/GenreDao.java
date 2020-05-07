package org.example.dao;

import org.example.Genre;

import javax.persistence.EntityManager;
import java.util.List;

public class GenreDao {
    private EntityManager entityManager;

    public GenreDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addGenre(Genre genre) {
        entityManager.getTransaction().begin();
        entityManager.persist(genre);
        entityManager.getTransaction().commit();
    }

    public void updateGenre(Genre genre, String newGenre) {
        entityManager.getTransaction().begin();
        genre.setName(newGenre);
        entityManager.merge(genre);
        entityManager.getTransaction().commit();
    }

    public void removeGenre(Genre genre) {
        entityManager.getTransaction().begin();
        entityManager.remove(genre);
        entityManager.getTransaction().commit();
    }

    public Genre getGenreId(int id) {
        entityManager.getTransaction().begin();
        Genre genre = entityManager.find(Genre.class, id);
        entityManager.getTransaction().commit();

        return genre;
    }

    public List<Genre> getListGenres() { return entityManager.createNamedQuery("Genre").getResultList(); }
}
