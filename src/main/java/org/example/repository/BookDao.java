package org.example.repository;

import org.example.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookDao extends CrudRepository<Book, Integer> {
}
