package org.egorkazantsev.library.service;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.exception.EntityAlreadyExistsException;
import org.egorkazantsev.library.exception.EntityIllegalArgumentException;
import org.egorkazantsev.library.exception.EntityNotFoundException;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Author;
import org.egorkazantsev.library.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    // get all
    public ResponseEntity<List<Author>> getAllAuthors() {
        // пустая ли коллекция
        var authors = authorRepository.findAllAuthors();
        if (authors.isEmpty())
            return new ResponseEntity<>(authors, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    // get by id
    public Author getAuthorById(UUID authorId) {
        if (authorId == null)
            throw new EntityIllegalArgumentException("Author ID cannot be null");

        // есть ли запись с указанным ИД
        Author author = authorRepository.findAuthorById(authorId);
        if (author == null)
            throw new EntityNotFoundException(Author.class.getSimpleName(), authorId);

        return author;
    }

    // add
    public UUID addAuthor(Author author) {
        if (author == null)
            throw new EntityIllegalArgumentException("Author cannot be null");

        // не пустой ли этот объект
        boolean attrsIsNull = Stream.of(author.getId(), author.getFullName(), author.getBio()).allMatch(Objects::isNull);
        if (attrsIsNull)
            throw new EntityIllegalArgumentException("Author attributes cannot be null");

        // существует ли уже запись с таким же ИД
        if (author.getId() != null) {
            Author existedAuthor = authorRepository.findAuthorById(author.getId());
            if (existedAuthor != null)
                throw new EntityAlreadyExistsException(Author.class.getSimpleName(), author.getId());
        }

        return authorRepository.insertAuthor(author);
    }

    // delete by id
    public HttpStatus deleteAuthor(UUID authorId) {
        // те же проверки, что и в get by id
        getAuthorById(authorId);
        authorRepository.deleteAuthorById(authorId);
        return HttpStatus.OK;
    }

    // update
    public UUID updateAuthor(Author author) {
        if (author == null)
            throw new EntityIllegalArgumentException("Author cannot be null");
        // ИД не null?
        if (author.getId() == null)
            throw new EntityIllegalArgumentException("Author ID cannot be null");
        // существует ли объект с таким ИД ?
        Author existedAuthor = authorRepository.findAuthorById(author.getId());
        if (existedAuthor == null)
            throw new EntityNotFoundException(Author.class.getSimpleName());

        return authorRepository.updateAuthor(author);
    }
}
