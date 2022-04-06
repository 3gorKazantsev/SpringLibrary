package org.egorkazantsev.library.controller;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.dto.InsertOrderDto;
import org.egorkazantsev.library.dto.OrderDto;
import org.egorkazantsev.library.jooq.generated.tables.pojos.BookOrder;
import org.egorkazantsev.library.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable UUID orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/add")
    public ResponseEntity<UUID> addOrder(@RequestBody InsertOrderDto orderDto) {
        return orderService.addOrder(orderDto);
    }

    @DeleteMapping("/delete/{orderId}")
    public HttpStatus deleteOrder(@PathVariable UUID orderId) {
        return orderService.deleteOrder(orderId);
    }

    @PutMapping("/update")
    public ResponseEntity<UUID> updateOrder(@RequestBody InsertOrderDto orderDto) {
        return orderService.updateOrder(orderDto);
    }
}
