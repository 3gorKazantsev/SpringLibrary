package org.egorkazantsev.library.repository;

import org.egorkazantsev.library.dto.OrderDto;
import org.egorkazantsev.library.jooq.generated.Tables;
import org.egorkazantsev.library.jooq.generated.tables.Author;
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
import java.util.UUID;

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
                .join(Tables.AUTHOR)
                    .on(Tables.BOOK.AUTHOR_ID.eq(Tables.AUTHOR.ID))
                .fetch();

        List<OrderDto> resultDto = new ArrayList<>();
        for (Record r : result) {
            var order = r.into(BookOrder.BOOK_ORDER);
            var reader = r.into(Reader.READER);
            var book = r.into(Book.BOOK);
            var author = r.into(Author.AUTHOR);
            resultDto.add(new OrderDto(order, reader, book, author));
        }

        return resultDto;
    }

    // get by id
    public OrderDto findOrderById(UUID id) {
        Record record = dslContext
                .select()
                .from(Tables.BOOK_ORDER)
                .join(Tables.READER)
                    .on(Tables.BOOK_ORDER.READER_ID.eq(Tables.READER.ID))
                .join(Tables.BOOK)
                    .on(Tables.BOOK_ORDER.BOOK_ID.eq(Tables.BOOK.ID))
                .join(Tables.AUTHOR)
                    .on(Tables.BOOK.AUTHOR_ID.eq(Tables.AUTHOR.ID))
                .where(Tables.BOOK_ORDER.ID.eq(id))
                .fetchOne();

        var order = record.into(BookOrder.BOOK_ORDER);
        var reader = record.into(Reader.READER);
        var book = record.into(Book.BOOK);
        var author = record.into(Author.AUTHOR);

        return new OrderDto(order, reader, book, author);
    }

    // insert
    public UUID insertOrder(org.egorkazantsev.library.jooq.generated.tables.pojos.BookOrder order) {
        orderDao.insert(order);
        return order.getId();
    }

    // delete
    public void deleteOrderById(UUID id) {
        orderDao.deleteById(id);
    }

    // update
    public UUID updateOrder(org.egorkazantsev.library.jooq.generated.tables.pojos.BookOrder order) {
        orderDao.update(order);
        return order.getId();
    }
}
