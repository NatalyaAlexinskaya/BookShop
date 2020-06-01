package org.example.service;

import org.example.controller.BookController;
import org.example.entities.Book;
import org.example.repository.BookDao;
import org.example.services.BookService;
import org.example.services.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BookServiceImplTest.TestService.class)
public class BookServiceImplTest {
    @Autowired
    BookService bookService;

    @MockBean
    Book book;

    @MockBean
    BookController bookController;

    @MockBean
    BookDao bookDao;

    @Test
    public void addBookTest() {
        book.setTitle("Test_title");
        book.setAuthor("Test_author");
        bookService.addBook(book);
    }

    @TestConfiguration
    public static class TestService {
        @Bean
        BookService bookService(BookDao bookDao) { return new BookServiceImpl(bookDao); }
    }
}
