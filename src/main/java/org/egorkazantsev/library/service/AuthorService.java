package org.egorkazantsev.library.service;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.jooq.generated.tables.daos.AuthorDao;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorDao authorDao;

    public List<Author> findAllAuthors() {
        return authorDao.findAll();
    }
}
