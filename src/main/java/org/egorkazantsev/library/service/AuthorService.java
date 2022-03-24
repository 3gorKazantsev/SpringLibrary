package org.egorkazantsev.library.service;

import org.egorkazantsev.library.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {

    public List<Author> getAuthors() {
        return List.of(
                new Author(UUID.randomUUID(),
                        "Эрих Мария Ремарк",
                        "Биография")
        );
    }
}