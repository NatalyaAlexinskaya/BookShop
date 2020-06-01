package org.example.services;

import org.example.entities.Book;

import java.util.Optional;

public interface BookService {
    void addBook(Book book);

    void removeBook(Book book);

    Optional<Book> getBookId(int id);

    Iterable<Book> getListBooks();
}
