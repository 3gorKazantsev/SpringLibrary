package org.egorkazantsev.library.controller;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.dto.BookDto;
import org.egorkazantsev.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/book", produces = "application/json; charset=UTF-8")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public BookDto getBookById(@PathVariable UUID bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping("/add")
    public UUID addBook(@RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @DeleteMapping("/delete/{bookId}")
    public HttpStatus deleteBook(@PathVariable UUID bookId) {
        return bookService.deleteBook(bookId);
    }

    @PutMapping("/update")
    public UUID updateBook(@RequestBody BookDto bookDto) {
        return bookService.updateBook(bookDto);
    }
}
