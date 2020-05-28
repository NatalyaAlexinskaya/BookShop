package org.example.repository;

import org.example.entities.Genre;

import java.util.List;

public interface GenreDao {
    void addGenre(Genre genre);

    void updateGenre(Genre genre);

    void removeGenre(Genre genre);

    Genre getGenreId(int id);

    List<Genre> getListGenres();
}
