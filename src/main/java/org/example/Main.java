package org.example;

import org.example.dao.BookDao;
import org.example.dao.CustomerDao;
import org.example.dao.GenreDao;
import org.example.entities.Book;
import org.example.entities.Customer;
import org.example.entities.Genre;

import javax.persistence.*;

public class Main {
    // Я использую базу данных H2, поэтому думаю менять ничего не придется в конфигурационном файле.

    public static BookDao bookDao = new BookDao();
    public static CustomerDao customerDao = new CustomerDao();
    public static GenreDao genreDao = new GenreDao();

    public static void main(String[] args) {
        //Создаю 3 книги
        Book book1 = getBook("Философский камень", "Джоан Роулинг");
        Book book2 = getBook("Тайная комната", "Джоан Роулинг");
        Book book3 = getBook("Кубок огня", "Джоан Роулинг");

        //Добавляю их в базу данных
        bookDao.addBook(book1);
        bookDao.addBook(book2);
        bookDao.addBook(book3);

//        bookDao.getListBooks();

        //Создаю жанр
        Genre genre1 = getGenre("Приключения");

        //Присваиваю книгам жанр
        book1.setGenre(genre1);
        book2.setGenre(genre1);
        book3.setGenre(genre1);

        //Создаю покупателя
        Customer customer1 = getCustomer("Иван Иванов");

        //Присваиваю книгам покупателя
        book1.setCustomer(customer1);
        book2.setCustomer(customer1);
        book3.setCustomer(customer1);

        //Добавляю в базу данных жанр и покупателя
        genreDao.addGenre(genre1);
        customerDao.addCustomer(customer1);

//        bookDao.getBookId(1);

        //Удаляю одну из книг
//        bookDao.removeBook(book2);

        //Запускаю Json
        JsonReader json = new JsonReader(bookDao);
        json.jsonRead();
        json.jsonWrite();
    }

    public static Book getBook(String title, String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);

        return  book;
    }

     public static Genre getGenre(String name) {
        Genre genre = new Genre();
        genre.setName(name);

        return genre;
     }

     public static Customer getCustomer(String name) {
        Customer customer = new Customer();
        customer.setName(name);

        return customer;
     }
}
