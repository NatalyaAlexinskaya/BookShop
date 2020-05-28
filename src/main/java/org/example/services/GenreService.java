package org.example.services;

import org.example.entities.Genre;

import java.util.Optional;

public interface GenreService {
    void addGenre(Genre genre);

    void removeGenre(Genre genre);

    Optional<Genre> getGenreId(int id);

    Iterable<Genre> getListGenres();
}
