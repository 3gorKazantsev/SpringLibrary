package org.egorkazantsev.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Book;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Reader;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderDto {

    private UUID id;
    private Reader reader;
    private Book book;
}
