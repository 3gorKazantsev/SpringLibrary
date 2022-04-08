package org.egorkazantsev.library.mapper;

import org.egorkazantsev.library.dto.BookForOrderDto;
import org.egorkazantsev.library.dto.OrderDto;
import org.egorkazantsev.library.jooq.generated.tables.pojos.BookOrder;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Reader;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.types.YearToSecond;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

import static org.egorkazantsev.library.jooq.generated.Tables.*;

@Component
public class OrderMapper implements RecordMapper<Record, OrderDto> {

    @Override
    public OrderDto map(Record record) {
        return new OrderDto(
                record.getValue(BOOK_ORDER.ID),
                new Reader(
                        record.getValue(READER.ID),
                        record.getValue(READER.FULL_NAME),
                        record.getValue(READER.ADDRESS),
                        record.getValue(READER.CONTACTS)
                ),
                new BookForOrderDto(
                        record.getValue(BOOK.ID),
                        record.getValue(BOOK.TITLE),
                        record.getValue(AUTHOR.FULL_NAME)
                ),
                LocalDate.from(record.getValue(BOOK_ORDER.BORROWING_DATE)),
                record.getValue(BOOK_ORDER.PERIOD).getDays()
        );
    }

    // from DTO to POJO
    public BookOrder toOrderPojo(OrderDto orderDto) {
        return new BookOrder(
                orderDto.getId(),
                orderDto.getReader() == null ? null : orderDto.getReader().getId(),
                orderDto.getBook() == null ? null : orderDto.getBook().getId(),
                orderDto.getBorrowingDate(),
                orderDto.getPeriod() == null ? null : YearToSecond.valueOf(Period.ofDays(orderDto.getPeriod()))
        );
    }
}
