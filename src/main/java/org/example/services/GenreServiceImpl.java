package org.example.services;

import org.example.entities.Genre;
import org.example.repository.GenreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public void addGenre(Genre genre) {
        genreDao.addGenre(genre);
    }

    @Override
    public void updateGenre(Genre genre) {
        genreDao.updateGenre(genre);
    }

    @Override
    public void removeGenre(Genre genre) {
        genreDao.removeGenre(genre);
    }

    @Override
    public Genre getGenreId(int id) {
        return genreDao.getGenreId(id);
    }

    @Override
    public List<Genre> getListGenres() {
        return genreDao.getListGenres();
    }
}
