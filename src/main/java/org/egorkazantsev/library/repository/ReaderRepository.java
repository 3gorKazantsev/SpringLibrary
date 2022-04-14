package org.egorkazantsev.library.repository;

import org.egorkazantsev.library.repository.generated.jooq.tables.daos.ReaderDao;
import org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ReaderRepository {

    private final DSLContext dslContext;
    private final ReaderDao readerDao;

    @Autowired
    public ReaderRepository(DefaultConfiguration configuration) {
        this.dslContext = new DefaultDSLContext(configuration);
        this.readerDao = new ReaderDao(configuration);
    }

    // find all
    public List<Reader> findAllReaders() {
        return readerDao.findAll();
    }

    // find by id
    public Reader findReaderById(UUID id) {
        return readerDao.findById(id);
    }

    // insert
    public UUID insertReader(Reader reader) {
        readerDao.insert(reader);
        return reader.getId();
    }

    // delete by id
    public void deleteReaderById(UUID id) {
        readerDao.deleteById(id);
    }

    // update
    public UUID updateReader(Reader reader) {
        readerDao.update(reader);
        return reader.getId();
    }
}
