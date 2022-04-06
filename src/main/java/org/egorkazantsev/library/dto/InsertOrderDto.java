package org.egorkazantsev.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.egorkazantsev.library.jooq.generated.tables.pojos.BookOrder;
import org.jooq.types.YearToSecond;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Data
@NoArgsConstructor
public class InsertOrderDto {

    private UUID id;
    private UUID readerId;
    private UUID bookId;
    private LocalDate borrowingDate;
    private Integer period;

    public BookOrder convertToBookOrder() {
        return new BookOrder(
                this.getId(),
                this.getReaderId(),
                this.getBookId(),
                this.getBorrowingDate(),
                YearToSecond.valueOf(Period.ofDays(this.getPeriod())));
    }
}
