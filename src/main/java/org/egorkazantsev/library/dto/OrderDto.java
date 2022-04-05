package org.egorkazantsev.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.egorkazantsev.library.dto.book.BookForOrderDto;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Reader;
import org.egorkazantsev.library.jooq.generated.tables.records.AuthorRecord;
import org.egorkazantsev.library.jooq.generated.tables.records.BookOrderRecord;
import org.egorkazantsev.library.jooq.generated.tables.records.BookRecord;
import org.egorkazantsev.library.jooq.generated.tables.records.ReaderRecord;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderDto {

    private UUID id;
    private Reader reader;
    private BookForOrderDto book;
    private LocalDate borrowingDate;
    private String duration;

    public OrderDto(BookOrderRecord order, ReaderRecord reader, BookRecord book, AuthorRecord author) {
        this.id = order.getId();
        this.reader = reader.into(Reader.class);
        this.book = new BookForOrderDto(book, author);
        this.borrowingDate = order.getBorrowingDate();

        var period = new Period(order.getDuration().toDuration().toMillis());
        this.duration = PeriodFormat.getDefault().print(period.normalizedStandard());
    }
}
