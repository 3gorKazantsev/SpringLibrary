package org.egorkazantsev.library.mapper;

import org.egorkazantsev.library.dto.AuthorDto;
import org.egorkazantsev.library.dto.BookDto;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Book;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static org.egorkazantsev.library.jooq.generated.Tables.*;

@Component
public class BookMapper implements RecordMapper<Record, BookDto> {

    @Override
    @Nullable
    public BookDto map(Record record) {
        return new BookDto(
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
    }

    // from DTO to POJO
    public Book toBookPojo(BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor() == null ? null : bookDto.getAuthor().getId(),
                bookDto.getDescription(),
                bookDto.getGenre(),
                bookDto.getStock()
        );
    }
}
