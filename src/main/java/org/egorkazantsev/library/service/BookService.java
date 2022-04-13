package org.egorkazantsev.library.service;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.dto.BookDto;
import org.egorkazantsev.library.exception.EntityAlreadyExistsException;
import org.egorkazantsev.library.exception.EntityIllegalArgumentException;
import org.egorkazantsev.library.exception.EntityNotFoundException;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Author;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Book;
import org.egorkazantsev.library.repository.AuthorRepository;
import org.egorkazantsev.library.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    // get all
    public ResponseEntity<List<BookDto>> getAllBooks() {
        // пустая ли коллекция
        var books = bookRepository.findAllBooks();
        if (books.isEmpty())
            return new ResponseEntity<>(books, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // get by id
    public BookDto getBookById(UUID bookId) {
        if (bookId == null)
            throw new EntityIllegalArgumentException("Book ID cannot be null");

        // есть ли запись с указанным ИД
        BookDto book = bookRepository.findBookById(bookId);
        if (book == null)
            throw new EntityNotFoundException(Book.class.getSimpleName(), bookId);

        return book;
    }

    // add
    public UUID addBook(BookDto bookDto) {
        if (bookDto == null)
            throw new EntityIllegalArgumentException("BookDto cannot be null");

        // не пустой ли этот объект
        boolean attrsIsNull = Stream.of(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getDescription(),
                bookDto.getGenre(),
                bookDto.getStock()
        ).allMatch(Objects::isNull);
        if (attrsIsNull)
            throw new EntityIllegalArgumentException("BookDto attributes cannot be null");

        // существует ли уже запись с таким же ИД
        if (bookDto.getId() != null) {
            BookDto existedBook = bookRepository.findBookById(bookDto.getId());
            if (existedBook != null)
                throw new EntityAlreadyExistsException(Book.class.getSimpleName(), bookDto.getId());
        }

        // todo если указывать null как автора, то сообщение верное, а код нет
        // существует ли указанный автор
        Author author = authorRepository.findAuthorById(bookDto.getAuthor().getId());
        if (author == null)
            throw new EntityNotFoundException(Author.class.getSimpleName(), bookDto.getAuthor().getId());

        // остаток меньше нуля ?
        if (bookDto.getStock() < 0)
            throw new EntityIllegalArgumentException("Stock cannot be less than 0");

        return bookRepository.insertBook(bookDto);
    }

    // delete by id
    public HttpStatus deleteBook(UUID bookId) {
        // те же проверки, что и в get by id
        getBookById(bookId);
        bookRepository.deleteBookById(bookId);
        return HttpStatus.OK;
    }

    // update
    public UUID updateBook(BookDto bookDto) {
        if (bookDto == null)
            throw new EntityIllegalArgumentException("BookDto cannot be null");

        // ИД не null ?
        if (bookDto.getId() == null)
            throw new EntityIllegalArgumentException("Book ID cannot be null");

        // существует ли объект с таким ИД ?
        BookDto existedBook = bookRepository.findBookById(bookDto.getId());
        if (existedBook == null)
            throw new EntityAlreadyExistsException(Book.class.getSimpleName(), bookDto.getId());

        // существует ли указанный автор
        if (bookDto.getAuthor() != null) {
            if (bookDto.getAuthor().getId() != null) {
                Author author = authorRepository.findAuthorById(bookDto.getAuthor().getId());
                if (author == null)
                    throw new EntityNotFoundException(Author.class.getSimpleName(), bookDto.getAuthor().getId());
            }
        }

        // остаток меньше нуля ?
        if (bookDto.getStock() < 0)
            throw new EntityIllegalArgumentException("Stock cannot be less than 0");

        return bookRepository.updateBook(bookDto);
    }
}
