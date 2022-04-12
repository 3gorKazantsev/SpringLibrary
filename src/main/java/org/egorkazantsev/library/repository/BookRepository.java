package org.egorkazantsev.library.repository;

import org.egorkazantsev.library.dto.BookDto;
import org.egorkazantsev.library.jooq.generated.tables.daos.BookDao;
import org.egorkazantsev.library.mapper.BookMapper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static org.egorkazantsev.library.jooq.generated.Tables.*;

import java.util.List;
import java.util.UUID;

@Repository
public class BookRepository {

    private final DSLContext dslContext;
    private final BookDao bookDao;
    private final BookMapper bookMapper;

    @Autowired
    public BookRepository(DefaultConfiguration configuration, BookMapper bookMapper) {
        this.dslContext = new DefaultDSLContext(configuration);
        this.bookDao = new BookDao(configuration);
        this.bookMapper = bookMapper;
    }

    // find all
    public List<BookDto> findAllBooks() {
        Result<Record> result = dslContext
                .select()
                .from(BOOK.join(AUTHOR)
                        .on(BOOK.AUTHOR_ID.eq(AUTHOR.ID)))
                .fetch();

        return result.map(bookMapper);
    }

    // get by id
    public BookDto findBookById(UUID id) {
        var result = dslContext
                .select()
                .from(BOOK.join(AUTHOR)
                        .on(BOOK.AUTHOR_ID.eq(AUTHOR.ID)))
                .where(BOOK.ID.eq(id))
                .fetchOne();

        return result != null ? result.map(bookMapper) : null;
    }

    // insert
    public UUID insertBook(BookDto bookDto) {
        var book = bookMapper.toBookPojo(bookDto);
        bookDao.insert(book);
        return book.getId();
    }

    // delete
    public void deleteBookById(UUID id) {
        bookDao.deleteById(id);
    }

    // update
    public UUID updateBook(BookDto bookDto) {
        var book = bookMapper.toBookPojo(bookDto);
        bookDao.update(book);
        return book.getId();
    }
}
