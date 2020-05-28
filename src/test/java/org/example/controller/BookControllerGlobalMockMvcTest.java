package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Book;
import org.example.repository.BookDao;
import org.example.testConfig.Config;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//Очень долго мучаюсь с тестами, пока к сожалению никак не поддаются, не понимаю в чем ошибка.

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        Config.class,
        BookController.class
})
@WebAppConfiguration
@EnableWebMvc
@TestPropertySource("classpath:application.properties")
public class BookControllerGlobalMockMvcTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private BookDao bookDao;

    @MockBean
    private BookController bookController;

    @Autowired
    private ApplicationContext context;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        Book book = new Book();
        book.setTitle("Test_book");
        book.setAuthor("TEST");
    }

    @Test
    public void test_GetAllBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/book/all")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test_GetBookById() throws Exception {
        int id = 1;

        mockMvc.perform(MockMvcRequestBuilders
                .get("/book/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test_DeleteBook() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/book/remove"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testRequestFor_UpdateAlbum() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Book book = new Book();
        book.setTitle("Test_book");
        book.setAuthor("TEST");
        String json = mapper.writeValueAsString(book);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/book/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testRequstFor_AddBook() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Book book = new Book();
        book.setTitle("Test_book");
        book.setAuthor("TEST");
        String json = mapper.writeValueAsString(book);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
