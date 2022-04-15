package org.egorkazantsev.library.controller;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.dto.OrderDto;
import org.egorkazantsev.library.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/order", produces = "application/json; charset=UTF-8")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@PathVariable UUID orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/add")
    public UUID addOrder(@RequestBody OrderDto orderDto) {
        return orderService.addOrder(orderDto);
    }

    @DeleteMapping("/delete/{orderId}")
    public HttpStatus deleteOrder(@PathVariable UUID orderId) {
        return orderService.deleteOrder(orderId);
    }

    @PutMapping("/update")
    public UUID updateOrder(@RequestBody OrderDto orderDto) {
        return orderService.updateOrder(orderDto);
    }
}
