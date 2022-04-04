package org.egorkazantsev.library.repository;

import org.egorkazantsev.library.dto.OrderDto;
import org.egorkazantsev.library.jooq.generated.Tables;
import org.egorkazantsev.library.jooq.generated.tables.Book;
import org.egorkazantsev.library.jooq.generated.tables.BookOrder;
import org.egorkazantsev.library.jooq.generated.tables.Reader;
import org.egorkazantsev.library.jooq.generated.tables.daos.BookOrderDao;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    private final DSLContext dslContext;
    private final BookOrderDao orderDao;

    @Autowired
    public OrderRepository(DefaultConfiguration configuration) {
        this.dslContext = new DefaultDSLContext(configuration);
        this.orderDao = new BookOrderDao(configuration);
    }

    // find all
    public List<OrderDto> findAllOrders() {
        Result<Record> result = dslContext
                .select()
                .from(Tables.BOOK_ORDER)
                .join(Tables.READER)
                    .on(Tables.BOOK_ORDER.READER_ID.eq(Tables.READER.ID))
                .join(Tables.BOOK)
                    .on(Tables.BOOK_ORDER.BOOK_ID.eq(Tables.BOOK.ID))
                .fetch();

        List<OrderDto> resultDto = new ArrayList<>();
        for (Record r : result) {
            var order = r.into(BookOrder.BOOK_ORDER);
            var reader = r.into(Reader.READER);
            var book = r.into(Book.BOOK);
            resultDto.add(new OrderDto());
        }

        return resultDto;
    }
}
