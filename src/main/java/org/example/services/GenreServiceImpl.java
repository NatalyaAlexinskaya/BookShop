package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entities.Genre;
import org.example.repository.GenreDao;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private GenreDao genreDao;

    @Override
    public void addGenre(Genre genre) {
        genreDao.save(genre);
    }

    @Override
    public void removeGenre(Genre genre) {
        genreDao.delete(genre);
    }

    @Override
    public Optional<Genre> getGenreId(int id) {
        return genreDao.findById(id);
    }

    @Override
    public Iterable<Genre> getListGenres() {
        return genreDao.findAll();
    }
}
