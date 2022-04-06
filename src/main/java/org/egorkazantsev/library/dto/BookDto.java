package org.egorkazantsev.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.egorkazantsev.library.dto.AuthorDto;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Author;
import org.egorkazantsev.library.jooq.generated.tables.records.AuthorRecord;
import org.egorkazantsev.library.jooq.generated.tables.records.BookRecord;

import java.util.UUID;

@Data
@NoArgsConstructor
public class BookDto {

    private UUID id;
    private String title;
    private AuthorDto author;
    private String description;
    private String genre;
    private Integer stock;

    public BookDto(BookRecord book, AuthorRecord author) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = author.into(AuthorDto.class);
        this.description = book.getDescription();
        this.genre = book.getGenre();
        this.stock = book.getStock();
    }
}
