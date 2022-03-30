package org.egorkazantsev.library.repository;

import org.egorkazantsev.library.jooq.generated.tables.daos.AuthorDao;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Author;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AuthorRepository {

    private final DSLContext dslContext;
    private final AuthorDao authorDao;

    @Autowired
    public AuthorRepository(DefaultConfiguration configuration) {
        this.dslContext = new DefaultDSLContext(configuration);
        this.authorDao = new AuthorDao(configuration);
    }

    // find all
    public List<Author> findAllAuthors() {
        return authorDao.findAll();
    }

    // find by id
    public Author findAuthorById(UUID id) {
        return authorDao.findById(id);
    }

    // insert
    public UUID insertAuthor(Author author) {
        authorDao.insert(author);
        return author.getId();
    }

    // delete by id
    public void deleteAuthorById(UUID id) {
        authorDao.deleteById(id);
    }

    // update
    public UUID updateAuthor(Author author) {
        authorDao.update(author);
        return author.getId();
    }
}
