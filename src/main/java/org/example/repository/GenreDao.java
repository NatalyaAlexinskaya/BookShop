package org.example.repository;

import org.example.entities.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreDao extends CrudRepository<Genre, Integer> {
}
