package org.egorkazantsev.library.service;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Reader;
import org.egorkazantsev.library.repository.ReaderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    // get all
    public ResponseEntity<List<Reader>> getAllReaders() {
        return new ResponseEntity<>(
                readerRepository.findAllReaders(),
                HttpStatus.OK);
    }

    // get by id
    public ResponseEntity<Reader> getReaderById(UUID readerId) {
        return new ResponseEntity<>(
                readerRepository.findReaderById(readerId),
                HttpStatus.OK
        );
    }

    // add
    public ResponseEntity<UUID> addReader(Reader reader) {
        UUID readerId = readerRepository.insertReader(reader);
        return new ResponseEntity<>(readerId, HttpStatus.OK);
    }

    // delete by id
    public HttpStatus deleteReader(UUID readerId) {
        readerRepository.deleteReaderById(readerId);
        return HttpStatus.OK;
    }

    // update
    public ResponseEntity<UUID> updateReader(Reader reader) {
        return new ResponseEntity<>(
                readerRepository.updateReader(reader),
                HttpStatus.OK
        );
    }
}
