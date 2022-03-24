package org.egorkazantsev.library.controller;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


}
