package org.egorkazantsev.library.repository;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.jooq.generated.Tables;
import org.egorkazantsev.library.jooq.generated.tables.daos.AuthorDao;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Author;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RequiredArgsConstructor
public class AuthorRepository {

    private final DSLContext dslContext;
    private final AuthorDao authorDao;

    @Autowired
    public AuthorRepository(DefaultConfiguration configuration) {
        this.dslContext = new DefaultDSLContext(configuration);
        this.authorDao = new AuthorDao(configuration);
    }

    public List<Author> findAllAuthors() {
        return authorDao.findAll();
        /*return dslContext
                .selectFrom(Tables.AUTHOR)
                .fetchInto(Author.class);*/
    }

    public String insertAuthor(Author author) {
        dslContext
                .insertInto(Tables.AUTHOR, Tables.AUTHOR.FULL_NAME, Tables.AUTHOR.BIO)
                .values(author.getFullName(), author.getBio())
                .execute();
        return "author added";
    }
}
