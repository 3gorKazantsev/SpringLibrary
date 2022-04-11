package org.egorkazantsev.library.service;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.exception.EntityIllegalArgumentException;
import org.egorkazantsev.library.exception.EntityNotFoundException;
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
    public List<Author> getAllAuthors() {
        return authorRepository.findAllAuthors();
    }

    // get by id
    public Author getAuthorById(UUID authorId) {
        if (authorId == null)
            throw new EntityIllegalArgumentException("Author ID cannot be null");

        Author author = authorRepository.findAuthorById(authorId);
        if (author == null)
            throw new EntityNotFoundException(Author.class.getTypeName(), authorId);

        return author;
    }

    // add
    public UUID addAuthor(Author author) {
        return authorRepository.insertAuthor(author);
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
