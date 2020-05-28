package org.example.services;

import org.example.entities.Genre;

import java.util.List;

public interface GenreService {
    void addGenre(Genre genre);

    void updateGenre(Genre genre);

    void removeGenre(Genre genre);

    Genre getGenreId(int id);

    List<Genre> getListGenres();
}
