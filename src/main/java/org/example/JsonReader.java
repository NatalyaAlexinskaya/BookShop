package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dao.BookDao;
import org.example.entities.Book;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

public class JsonReader {
    BookDao bookDao;

    public JsonReader(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    //Чтение данных из файла с форматом *json и запись в БД
    public void jsonRead() {
        URL url = this.getClass().getClassLoader().getResource("books.json");

        if (url != null) {
            File jsonFile = null;
            try {
                jsonFile = Paths.get(url.toURI()).toFile();
                ObjectMapper objectMapper = new ObjectMapper();

                List<Book> books = objectMapper.readValue(jsonFile, new TypeReference<List<Book>>() {
                });

                for (Book book : books) {
                    bookDao.addBook(book);
                }
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Форматирование данных из БД в JSON
    public void jsonWrite() {
        List<Book> books = bookDao.getListBooks();
        ObjectMapper objectMapper = new ObjectMapper();

        for (Book book : books) {
            try {
                System.out.println(objectMapper.writeValueAsString(book));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
