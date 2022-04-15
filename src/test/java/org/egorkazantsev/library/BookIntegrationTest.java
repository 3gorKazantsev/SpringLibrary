package org.egorkazantsev.library;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.egorkazantsev.library.dto.AuthorDto;
import org.egorkazantsev.library.dto.BookDto;
import org.egorkazantsev.library.repository.BookRepository;
import org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Author;
import org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Book;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(scripts = {"/db-scripts/create-schema.sql", "/db-scripts/populate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db-scripts/delete-schema.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BookIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository repository;

    @Test
    public void getAllTest() throws Exception {
        var size = repository.findAllBooks().size();
        mockMvc.perform(get("/api/book/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(size)));
    }

//    @Test
//    @Sql(scripts = {"/db-scripts/clear-book-table.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
////    @Sql(scripts = {"/db-scripts/populate.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//    public void getAllIfEmpty() throws Exception {
//        mockMvc.perform(get("/api/book/all"))
//                .andExpect(status().isNoContent());
//    }

    @Test
    public void getByIdTest() throws Exception {
        mockMvc.perform(get("/api/book/{id}", "4dabe23b-4a4d-4afd-95c5-a5b373e83c10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title")
                        .value("Три товарища"));
        mockMvc.perform(get("/api/book/{id}", "b3924f63-b2d3-425e-87f0-6cf90bd8c83c"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description")
                        .value("Описание книги Рассказы"));
    }

    @Test
    public void getByWrongIdTest() throws Exception {
        UUID id = UUID.fromString("4dabe23b-4a4d-4afd-95c5-a5b373e83c11");
        mockMvc.perform(get("/api/book/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(String.format("Entity '%s' with ID '%s' not found",
                                Book.class.getSimpleName(), id)));
        String notId = "hello";
        mockMvc.perform(get("/api/book/{id}", notId))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("Argument type mismatch"));
    }

    @Test
    public void addBookTest() throws Exception {
        BookDto bookDto = new BookDto(
                null,
                "title",
                new AuthorDto(UUID.fromString("4b8f2019-c44a-4b27-ae3e-9ff38ae52167"), "fullName"),
                "description",
                "genre",
                12);
        String json = new ObjectMapper().writeValueAsString(bookDto);
        MvcResult result = mockMvc.perform(post("/api/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        content = content.replaceAll("\"", "");
        assertThat(UUID.fromString(content)).isNotNull();
    }

    @Test
    public void addNullBookTest() throws Exception {
        mockMvc.perform(post("/api/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("BookDto attributes cannot be null"));
    }

    @Test
    public void addExistingBookTest() throws Exception {
        UUID id = UUID.fromString("4dabe23b-4a4d-4afd-95c5-a5b373e83c10");
        BookDto bookDto = new BookDto(
                id,
                "title",
                new AuthorDto(
                        UUID.fromString("4b8f2019-c44a-4b27-ae3e-9ff38ae52167"),
                        "fullName"),
                "description",
                "genre",
                12);
        String json = new ObjectMapper().writeValueAsString(bookDto);
        mockMvc.perform(post("/api/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message")
                        .value(String.format("Entity '%s' with ID '%s' already exists",
                                Book.class.getSimpleName(), id)));
    }

    @Test
    public void addBookWithExistingAuthorTest() throws Exception {
        UUID authorId = UUID.fromString("4dabe23b-4a4d-4afd-95c5-a5b373e83c10");
        BookDto bookDto = new BookDto(
                null,
                "title",
                new AuthorDto(
                        authorId,
                        "fullName"),
                "description",
                "genre",
                12);
        String json = new ObjectMapper().writeValueAsString(bookDto);
        mockMvc.perform(post("/api/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(String.format("Entity '%s' with ID '%s' not found",
                                Author.class.getSimpleName(), authorId)));
    }

    @Test
    public void addBookWithNullAuthorTest() throws Exception {
        BookDto bookDto = new BookDto(
                null,
                "title",
                new AuthorDto(
                        null,
                        "fullName"),
                "description",
                "genre",
                12);
        String json = new ObjectMapper().writeValueAsString(bookDto);
        mockMvc.perform(post("/api/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void addBookStockLessThan0Test() throws Exception {
        BookDto bookDto = new BookDto(
                null,
                "title",
                new AuthorDto(
                        UUID.fromString("4b8f2019-c44a-4b27-ae3e-9ff38ae52167"),
                        "fullName"),
                "description",
                "genre",
                -1);
        String json = new ObjectMapper().writeValueAsString(bookDto);
        mockMvc.perform(post("/api/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("Stock cannot be less than 0"));
    }

    @Test
    public void updateBookTest() throws Exception {
        UUID id = UUID.fromString("b3924f63-b2d3-425e-87f0-6cf90bd8c83c");
        UUID authorId = UUID.fromString("4b8f2019-c44a-4b27-ae3e-9ff38ae52167");
        BookDto bookDto = new BookDto(
                id,
                "title",
                new AuthorDto(
                        authorId,
                        null),
                "description",
                "genre",
                1);
        String json = new ObjectMapper().writeValueAsString(bookDto);
        MvcResult result = mockMvc.perform(put("/api/book/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        content = content.replaceAll("\"", "");
        assertThat(UUID.fromString(content)).isEqualTo(id);
    }

    @Test
    public void updateBookWithoutAuthorTest() throws Exception {
        UUID id = UUID.fromString("b3924f63-b2d3-425e-87f0-6cf90bd8c83c");
        BookDto bookDto = new BookDto(
                id,
                "title",
                null,
                "description",
                "genre",
                1);
        String json = new ObjectMapper().writeValueAsString(bookDto);

        MvcResult result = mockMvc.perform(put("/api/book/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        content = content.replaceAll("\"", "");
        assertThat(UUID.fromString(content)).isEqualTo(id);
    }

    @Test
    public void updateBookOnlyAuthorTest() throws Exception {
        UUID id = UUID.fromString("b3924f63-b2d3-425e-87f0-6cf90bd8c83c");
        UUID authorId = UUID.fromString("4b8f2019-c44a-4b27-ae3e-9ff38ae52167");
        BookDto bookDto = new BookDto(
                id,
                null,
                new AuthorDto(
                        authorId,
                        null
                ),
                null,
                null,
                null);
        String json = new ObjectMapper().writeValueAsString(bookDto);

        MvcResult result = mockMvc.perform(put("/api/book/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        content = content.replaceAll("\"", "");
        assertThat(UUID.fromString(content)).isEqualTo(id);
    }


    @Test
    public void updateBookIdNullTest() throws Exception {
        BookDto bookDto = new BookDto(
                null,
                null,
                null,
                null,
                null,
                null);
        String json = new ObjectMapper().writeValueAsString(bookDto);

        mockMvc.perform(put("/api/book/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("Book ID cannot be null"));
    }

    @Test
    public void updateUnknownBookTest() throws Exception {
        UUID id = UUID.randomUUID();
        BookDto bookDto = new BookDto(
                id,
                null,
                null,
                null,
                null,
                null);
        String json = new ObjectMapper().writeValueAsString(bookDto);

        mockMvc.perform(put("/api/book/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(String.format("Entity '%s' with ID '%s' not found",
                                Book.class.getSimpleName(), id)));
    }

    @Test
    public void updateBookWithUnknownAuthorTest() throws Exception {
        UUID id = UUID.fromString("b3924f63-b2d3-425e-87f0-6cf90bd8c83c");
        UUID authorId = UUID.randomUUID();
        BookDto bookDto = new BookDto(
                id,
                null,
                new AuthorDto(authorId, null),
                null,
                null,
                null);
        String json = new ObjectMapper().writeValueAsString(bookDto);

        mockMvc.perform(put("/api/book/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(String.format("Entity '%s' with ID '%s' not found",
                                Author.class.getSimpleName(), authorId)));
    }

    @Test
    public void updateBookStockLessThan0Test() throws Exception {
        UUID id = UUID.fromString("b3924f63-b2d3-425e-87f0-6cf90bd8c83c");
        BookDto bookDto = new BookDto(
                id,
                null,
                null,
                null,
                null,
                -1);
        String json = new ObjectMapper().writeValueAsString(bookDto);

        mockMvc.perform(put("/api/book/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("Stock cannot be less than 0"));
    }
}