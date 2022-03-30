package org.egorkazantsev.library.service;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Author;
import org.egorkazantsev.library.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    // get all
    public ResponseEntity<List<Author>> getAllAuthors() {
        return new ResponseEntity<>(
                authorRepository.findAllAuthors(),
                HttpStatus.OK);
    }

    // get by id
    public ResponseEntity<Author> getAuthorById(UUID authorId) {
        return new ResponseEntity<>(
                authorRepository.findAuthorById(authorId),
                HttpStatus.OK);
    }

    // add
    public ResponseEntity<UUID> addAuthor(Author author) {
        UUID authorId = authorRepository.insertAuthor(author);
        return new ResponseEntity<>(authorId, HttpStatus.OK);
    }

    // delete by id
    public HttpStatus deleteAuthor(UUID authorId) {
        authorRepository.deleteAuthorById(authorId);
        return HttpStatus.OK;
    }

    // update
    public ResponseEntity<UUID> updateAuthor(Author author) {
        return new ResponseEntity<>(
                authorRepository.updateAuthor(author),
                HttpStatus.OK);
    }
}
