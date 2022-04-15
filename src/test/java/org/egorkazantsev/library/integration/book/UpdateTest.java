package org.egorkazantsev.library.integration.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.egorkazantsev.library.Utils;
import org.egorkazantsev.library.dto.AuthorDto;
import org.egorkazantsev.library.dto.BookDto;
import org.egorkazantsev.library.repository.BookRepository;
import org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Author;
import org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Book;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(scripts = {"/db-scripts/create-schema.sql", "/db-scripts/populate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db-scripts/delete-schema.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UpdateTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository repository;

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

        String content = Utils.getUuid(result);
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

        String content = Utils.getUuid(result);
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

        String content = Utils.getUuid(result);
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
