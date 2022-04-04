package org.egorkazantsev.library.repository;

import org.egorkazantsev.library.dto.BookDto;
import org.egorkazantsev.library.jooq.generated.Tables;
import org.egorkazantsev.library.jooq.generated.tables.Author;
import org.egorkazantsev.library.jooq.generated.tables.Book;
import org.egorkazantsev.library.jooq.generated.tables.daos.BookDao;
import org.egorkazantsev.library.jooq.generated.tables.records.AuthorRecord;
import org.egorkazantsev.library.jooq.generated.tables.records.BookRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class BookRepository {

    private final DSLContext dslContext;
    private final BookDao bookDao;

    @Autowired
    public BookRepository(DefaultConfiguration configuration) {
        this.dslContext = new DefaultDSLContext(configuration);
        this.bookDao = new BookDao(configuration);
    }

    // find all
    public List<BookDto> findAllBooks() {
        Result<Record> result = dslContext
                .select()
                .from(Tables.BOOK.join(Tables.AUTHOR)
                        .on(Tables.BOOK.AUTHOR_ID.eq(Tables.AUTHOR.ID)))
                .fetch();

        List<BookDto> resultDto = new ArrayList<>();
        for (Record r : result) {
            var book = r.into(Book.BOOK);
            var author = r.into(Author.AUTHOR);
            resultDto.add(new BookDto(book, author));
        }

        return resultDto;
    }

    // get by id
    public BookDto findBookById(UUID id) {
        Record record = dslContext
                .select()
                .from(Tables.BOOK.join(Tables.AUTHOR)
                        .on(Tables.BOOK.AUTHOR_ID.eq(Tables.AUTHOR.ID)))
                .where(Book.BOOK.ID.eq(id))
                .fetchOne();

        BookRecord book = record.into(Book.BOOK);
        AuthorRecord author = record.into(Author.AUTHOR);

        return new BookDto(book, author);
    }

    // insert
    public UUID insertBook(org.egorkazantsev.library.jooq.generated.tables.pojos.Book book) {
        bookDao.insert(book);
        return book.getId();
    }

    // delete
    public void deleteBookById(UUID id) {
        bookDao.deleteById(id);
    }

    // update
    public UUID updateBook(org.egorkazantsev.library.jooq.generated.tables.pojos.Book book) {
        bookDao.update(book);
        return book.getId();
    }
}
