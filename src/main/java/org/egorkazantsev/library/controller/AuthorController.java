package org.egorkazantsev.library.controller;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.exception.EntityNotFoundException;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Author;
import org.egorkazantsev.library.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/all")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable UUID authorId) {
        return authorService.getAuthorById(authorId);
    }

    @PostMapping("/add")
    public UUID addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @DeleteMapping("/delete/{authorId}")
    public HttpStatus deleteAuthor(@PathVariable UUID authorId) {
        return authorService.deleteAuthor(authorId);
    }

    @PutMapping("/update")
    public ResponseEntity<UUID> updateAuthor(@RequestBody Author author) {
        return authorService.updateAuthor(author);
    }
}

