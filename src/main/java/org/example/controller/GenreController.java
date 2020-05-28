package org.example.controller;

import org.example.entities.Genre;
import org.example.repository.GenreDao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "genre")
public class GenreController {
    private GenreDao genreDao;

    public GenreController(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @GetMapping(path = "/all")
    public List<Genre> getGenres() {
        return genreDao.getListGenres();
    }

    @GetMapping(path = "/{id}")
    public Genre getById(@PathVariable(name = "id") Integer id) {
        return genreDao.getGenreId(id);
    }

    @PostMapping(path = "/add")
    public void addGenre(@RequestBody Genre genre) {
        genreDao.addGenre(genre);
    }

    @PutMapping(path = "/update")
    public void updateGenre(@RequestBody Genre genre) {
        genreDao.updateGenre(genre);
    }

    @DeleteMapping(path = "/remove")
    public void removeGenre(@RequestBody Genre genre) {
        genreDao.removeGenre(genre);
    }
}
