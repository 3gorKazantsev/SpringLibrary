package org.egorkazantsev.library.service;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.dto.InsertOrderDto;
import org.egorkazantsev.library.dto.OrderDto;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Book;
import org.egorkazantsev.library.jooq.generated.tables.pojos.BookOrder;
import org.egorkazantsev.library.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    // get all
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return new ResponseEntity<>(
                orderRepository.findAllOrders(),
                HttpStatus.OK);
    }

    // get by id
    public ResponseEntity<OrderDto> getOrderById(UUID orderId) {
        return new ResponseEntity<>(
                orderRepository.findOrderById(orderId),
                HttpStatus.OK
        );
    }

    // add
    public ResponseEntity<UUID> addOrder(InsertOrderDto orderDto) {
        UUID orderId = orderRepository.insertOrder(orderDto);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    // delete by id
    public HttpStatus deleteOrder(UUID orderId) {
        orderRepository.deleteOrderById(orderId);
        return HttpStatus.OK;
    }

    // update
    public ResponseEntity<UUID> updateOrder(InsertOrderDto orderDto) {
        return new ResponseEntity<>(
                orderRepository.updateOrder(orderDto),
                HttpStatus.OK
        );
    }
}
