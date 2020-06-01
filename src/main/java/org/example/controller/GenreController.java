package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entities.Genre;
import org.example.services.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "genre")
public class GenreController {
    private GenreService genreService;

    @GetMapping(path = "/all")
    public Iterable<Genre> getGenres() {
        return genreService.getListGenres();
    }

    @GetMapping(path = "/{id}")
    public Optional<Genre> getById(@PathVariable(name = "id") Integer id) {
        return genreService.getGenreId(id);
    }

    @PostMapping(path = "/add")
    public void addGenre(@RequestBody Genre genre) {
        genreService.addGenre(genre);
    }

    @DeleteMapping(path = "/remove")
    public void removeGenre(@RequestBody Genre genre) {
        genreService.removeGenre(genre);
    }
}
