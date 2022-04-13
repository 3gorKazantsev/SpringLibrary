package org.egorkazantsev.library.service;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.dto.BookDto;
import org.egorkazantsev.library.dto.OrderDto;
import org.egorkazantsev.library.exception.EntityAlreadyExistsException;
import org.egorkazantsev.library.exception.EntityIllegalArgumentException;
import org.egorkazantsev.library.exception.EntityNotFoundException;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Book;
import org.egorkazantsev.library.jooq.generated.tables.pojos.BookOrder;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Reader;
import org.egorkazantsev.library.repository.BookRepository;
import org.egorkazantsev.library.repository.OrderRepository;
import org.egorkazantsev.library.repository.ReaderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;

    // get all
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        // пустая ли коллекция
        var orders = orderRepository.findAllOrders();
        if (orders.isEmpty())
            return new ResponseEntity<>(orders, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // get by id
    public OrderDto getOrderById(UUID orderId) {
        if (orderId == null)
            throw new EntityIllegalArgumentException("Order ID cannot be null");

        // есть ли запись с указанным ИД
        OrderDto order = orderRepository.findOrderById(orderId);
        if (order == null)
            throw new EntityNotFoundException(BookOrder.class.getSimpleName(), orderId);

        return order;
    }

    // add todo разобраться почему ошибка идет не туда
    public UUID addOrder(OrderDto orderDto) {
        if (orderDto == null)
            throw new EntityIllegalArgumentException("OrderDto cannot be null");

        // не пустой ли это объект
        boolean attrsIsNull = Stream.of(
                orderDto.getId(),
                orderDto.getReader(),
                orderDto.getBook(),
                orderDto.getBorrowingDate(),
                orderDto.getPeriod()
        ).allMatch(Objects::isNull);
        if (attrsIsNull)
            throw new EntityIllegalArgumentException("OrderDto attributes cannot be null");

        // существует ли уже запись с таким же ИД
        if (orderDto.getId() != null) {
            OrderDto existedOrder = orderRepository.findOrderById(orderDto.getId());
            if (existedOrder != null)
                throw new EntityAlreadyExistsException(BookOrder.class.getSimpleName(), orderDto.getId());
        }

        // существует ли указанный читатель
        Reader reader = readerRepository.findReaderById(orderDto.getReader().getId());
        if (reader == null)
            throw new EntityNotFoundException(Reader.class.getSimpleName(), orderDto.getReader().getId());

        // существует ли указанная книга
        BookDto book = bookRepository.findBookById(orderDto.getBook().getId());
        if (book == null)
            throw new EntityNotFoundException(Book.class.getSimpleName(), orderDto.getBook().getId());

        return orderRepository.insertOrder(orderDto);
    }

    // delete by id
    public HttpStatus deleteOrder(UUID orderId) {
        // те же проверки, что и в get by id
        getOrderById(orderId);
        orderRepository.deleteOrderById(orderId);
        return HttpStatus.OK;
    }

    // update
    public UUID updateOrder(OrderDto orderDto) {
        if (orderDto == null)
            throw new EntityIllegalArgumentException("OrderDto cannot be null");

        // ИД не null ?
        if (orderDto.getId() == null)
            throw new EntityIllegalArgumentException("Order ID cannot be null");

        // уже существует объект с таким ИД ?
        OrderDto existedOrder = orderRepository.findOrderById(orderDto.getId());
        if (existedOrder != null)
            throw new EntityAlreadyExistsException(BookOrder.class.getSimpleName(), orderDto.getId());

        // существует ли указанный читатель
        Reader reader = readerRepository.findReaderById(orderDto.getReader().getId());
        if (reader == null)
            throw new EntityNotFoundException(Reader.class.getSimpleName(), orderDto.getReader().getId());

        // существует ли указанная книга
        BookDto book = bookRepository.findBookById(orderDto.getBook().getId());
        if (book == null)
            throw new EntityNotFoundException(Book.class.getSimpleName(), orderDto.getBook().getId());

        return orderRepository.updateOrder(orderDto);
    }
}
