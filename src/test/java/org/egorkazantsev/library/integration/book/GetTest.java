package org.egorkazantsev.library.integration.book;

import org.egorkazantsev.library.repository.BookRepository;
import org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(scripts = {"/db-scripts/create-schema.sql", "/db-scripts/populate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db-scripts/delete-schema.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class GetTest {

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
        UUID id = UUID.randomUUID();
        mockMvc.perform(get("/api/book/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(String.format("Entity '%s' with ID '%s' not found",
                                Book.class.getSimpleName(), id)));
    }

    @Test
    public void getByNotId() throws Exception {
        String notId = "hello";
        mockMvc.perform(get("/api/book/{id}", notId))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("Argument type mismatch"));
    }
}
