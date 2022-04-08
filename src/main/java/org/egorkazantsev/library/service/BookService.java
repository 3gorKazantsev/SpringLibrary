package org.egorkazantsev.library.service;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.dto.BookDto;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Book;
import org.egorkazantsev.library.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // get all
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return new ResponseEntity<>(
                bookRepository.findAllBooks(),
                HttpStatus.OK);
    }

    // get by id
    public ResponseEntity<BookDto> getBookById(UUID bookId) {
        return new ResponseEntity<>(
                bookRepository.findBookById(bookId),
                HttpStatus.OK);
    }

    // add      TODO проверка существует ли уже такая запись с таким же ид
    public ResponseEntity<UUID> addBook(BookDto bookDto) {
        UUID bookId = bookRepository.insertBook(bookDto);
        return new ResponseEntity<>(bookId, HttpStatus.OK);
    }

    // delete by id     TODO сделать проверку удалилось ли и только потом отправлять хттп статус
    public HttpStatus deleteBook(UUID bookId) {
        bookRepository.deleteBookById(bookId);
        return HttpStatus.OK;
    }

    // update
    public ResponseEntity<UUID> updateBook(BookDto bookDto) {
        return new ResponseEntity<>(
                bookRepository.updateBook(bookDto),
                HttpStatus.OK);
    }
}
