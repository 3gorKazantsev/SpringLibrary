package org.egorkazantsev.library.controller;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


}
