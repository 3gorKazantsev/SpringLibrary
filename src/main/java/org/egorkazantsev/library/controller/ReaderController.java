package org.egorkazantsev.library.controller;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader;
import org.egorkazantsev.library.service.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/reader", produces = "application/json; charset=UTF-8")
public class ReaderController {

    private final ReaderService readerService;

    @GetMapping("/all")
    public ResponseEntity<List<Reader>> getAllReaders() {
        return readerService.getAllReaders();
    }

    @GetMapping("/{readerId}")
    public Reader getReaderById(@PathVariable UUID readerId) {
        return readerService.getReaderById(readerId);
    }

    @PostMapping("/add")
    public UUID addReader(@RequestBody Reader reader) {
        return readerService.addReader(reader);
    }

    @DeleteMapping("/delete/{readerId}")
    public HttpStatus deleteReader(@PathVariable UUID readerId) {
        return readerService.deleteReader(readerId);
    }

    @PutMapping("/update")
    public UUID updateReader(@RequestBody Reader reader) {
        return readerService.updateReader(reader);
    }
}
