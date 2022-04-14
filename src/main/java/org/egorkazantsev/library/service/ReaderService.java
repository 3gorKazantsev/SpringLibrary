package org.egorkazantsev.library.service;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.exception.EntityAlreadyExistsException;
import org.egorkazantsev.library.exception.EntityIllegalArgumentException;
import org.egorkazantsev.library.exception.EntityNotFoundException;
import org.egorkazantsev.library.repository.ReaderRepository;
import org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    // get all
    public ResponseEntity<List<Reader>> getAllReaders() {
        // пустая ли коллекция
        var readers = readerRepository.findAllReaders();
        if (readers.isEmpty())
            return new ResponseEntity<>(readers, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(readers, HttpStatus.OK);
    }

    // get by id
    public Reader getReaderById(UUID readerId) {
        if (readerId == null)
            throw new EntityIllegalArgumentException("Reader ID cannot be null");

        // есть ли запись с указанным ИД
        Reader reader = readerRepository.findReaderById(readerId);
        if (reader == null)
            throw new EntityNotFoundException(Reader.class.getSimpleName(), readerId);

        return reader;
    }

    // add
    public UUID addReader(Reader reader) {
        if (reader == null)
            throw new EntityIllegalArgumentException("Reader cannot be null");

        // не пустой ли этот объект
        boolean attrsIsNull = Stream.of(reader.getId(), reader.getFullName(), reader.getAddress(), reader.getContacts()).allMatch(Objects::isNull);
        if (attrsIsNull)
            throw new EntityIllegalArgumentException("Reader attributes cannot be null");

        // существует ли уже запись с таким же ИД
        if (reader.getId() != null) {
            Reader existedReader = readerRepository.findReaderById(reader.getId());
            if (existedReader != null)
                throw new EntityAlreadyExistsException(Reader.class.getSimpleName(), reader.getId());
        }

        return readerRepository.insertReader(reader);
    }

    // delete by id
    public HttpStatus deleteReader(UUID readerId) {
        // те же проверки, что и в get by id
        getReaderById(readerId);
        readerRepository.deleteReaderById(readerId);
        return HttpStatus.OK;
    }

    // update
    public UUID updateReader(Reader reader) {
        if (reader == null)
            throw new EntityIllegalArgumentException("Reader cannot be null");

        // ИД не null?
        if (reader.getId() == null)
            throw new EntityIllegalArgumentException("Reader ID cannot be null");

        // есть ли объект с таким ИД ?
        Reader existedReader = readerRepository.findReaderById(reader.getId());
        if (existedReader == null)
            throw new EntityNotFoundException(Reader.class.getSimpleName(), reader.getId());

        return readerRepository.updateReader(reader);
    }
}
