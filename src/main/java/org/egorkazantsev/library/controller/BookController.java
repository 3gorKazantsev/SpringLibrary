package org.egorkazantsev.library.controller;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.dto.book.BookDto;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Book;
import org.egorkazantsev.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable UUID bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping("/add")
    public ResponseEntity<UUID> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @DeleteMapping("/delete/{bookId}")
    public HttpStatus deleteBook(@PathVariable UUID bookId) {
        return bookService.deleteBook(bookId);
    }

    @PutMapping("/update")
    public ResponseEntity<UUID> updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }
}
