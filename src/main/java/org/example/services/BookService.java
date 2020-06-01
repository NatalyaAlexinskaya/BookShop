package org.example.services;

import org.example.entities.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    void updateBook(Book book);

    void removeBook(Book book);

    Book getBookId(int id);

    List<Book> getListBooks();
}
