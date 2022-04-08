package org.egorkazantsev.library.repository;

import org.egorkazantsev.library.dto.OrderDto;
import org.egorkazantsev.library.jooq.generated.tables.BookOrder;
import org.egorkazantsev.library.jooq.generated.tables.daos.BookOrderDao;
import org.egorkazantsev.library.jooq.generated.tables.records.BookOrderRecord;
import org.egorkazantsev.library.mapper.OrderMapper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static org.egorkazantsev.library.jooq.generated.Tables.*;

@Repository
public class OrderRepository {

    private final DSLContext dslContext;
    private final BookOrderDao orderDao;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderRepository(DefaultConfiguration configuration, OrderMapper orderMapper) {
        this.dslContext = new DefaultDSLContext(configuration);
        this.orderDao = new BookOrderDao(configuration);
        this.orderMapper = orderMapper;
    }

    // find all
    public List<OrderDto> findAllOrders() {
        Result<Record> result = dslContext
                .select()
                .from(BOOK_ORDER)
                .join(READER)
                    .on(BOOK_ORDER.READER_ID.eq(READER.ID))
                .join(BOOK)
                    .on(BOOK_ORDER.BOOK_ID.eq(BOOK.ID))
                .join(AUTHOR)
                    .on(BOOK.AUTHOR_ID.eq(AUTHOR.ID))
                .fetch();

        return result.map(orderMapper);
    }

    // get by id
    public OrderDto findOrderById(UUID id) {
        Record record = dslContext
                .select()
                .from(BOOK_ORDER)
                .join(READER)
                    .on(BOOK_ORDER.READER_ID.eq(READER.ID))
                .join(BOOK)
                    .on(BOOK_ORDER.BOOK_ID.eq(BOOK.ID))
                .join(AUTHOR)
                    .on(BOOK.AUTHOR_ID.eq(AUTHOR.ID))
                .where(BOOK_ORDER.ID.eq(id))
                .fetchOne();

        return record.map(orderMapper);
    }

    // insert
    public UUID insertOrder(OrderDto orderDto) {
        var order = orderMapper.toOrderPojo(orderDto);
        orderDao.insert(order);
        return order.getId();
    }

    // delete
    public void deleteOrderById(UUID id) {
        orderDao.deleteById(id);
    }

    // update
    public UUID updateOrder(OrderDto orderDto) {
        var order = orderMapper.toOrderPojo(orderDto);
        orderDao.update(order);
        return order.getId();
    }
}
