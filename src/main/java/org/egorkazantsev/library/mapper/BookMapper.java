package org.egorkazantsev.library.mapper;

import org.egorkazantsev.library.dto.AuthorDto;
import org.egorkazantsev.library.dto.BookDto;
import org.egorkazantsev.library.jooq.generated.Tables;
import org.egorkazantsev.library.jooq.generated.tables.Author;
import org.egorkazantsev.library.jooq.generated.tables.Book;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements RecordMapper<Record, BookDto> {

    private final Book BOOK = Tables.BOOK;
    private final Author AUTHOR = Tables.AUTHOR;

    @Override
    public BookDto map(Record record) {
        BookDto bookDto = new BookDto(
                record.getValue(BOOK.ID),
                record.getValue(BOOK.TITLE),
                new AuthorDto(
                        record.getValue(AUTHOR.ID),
                        record.getValue(AUTHOR.FULL_NAME)
                ),
                record.getValue(BOOK.DESCRIPTION),
                record.getValue(BOOK.GENRE),
                record.getValue(BOOK.STOCK)
        );

        return bookDto;
    }
}
