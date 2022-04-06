package org.egorkazantsev.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.egorkazantsev.library.jooq.generated.tables.records.AuthorRecord;
import org.egorkazantsev.library.jooq.generated.tables.records.BookRecord;

import java.util.UUID;

@Data
@NoArgsConstructor
public class BookForOrderDto {

    private UUID id;
    private String title;
    private String author;

    public BookForOrderDto(BookRecord book, AuthorRecord author) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = author.getFullName();
    }
}
