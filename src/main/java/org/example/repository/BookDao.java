package org.example.repository;

import org.example.entities.Book;

import java.util.List;

public interface BookDao {
    void addBook(Book book);

    void updateBook(Book book);

    void removeBook(Book book);

    Book getBookId(Integer id);

    List<Book> getListBooks();
}
