package org.egorkazantsev.library.repository;

import org.egorkazantsev.library.jooq.generated.tables.daos.ReaderDao;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Reader;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReaderRepository {

    private final DSLContext dslContext;
    private final ReaderDao readerDao;

    @Autowired
    public ReaderRepository(DefaultConfiguration configuration) {
        this.dslContext = new DefaultDSLContext(configuration);
        this.readerDao = new ReaderDao(configuration);
    }

    public List<Reader> findAllReaders() {
        return readerDao.findAll();
    }

    public String insertReader(Reader reader) {
        readerDao.insert(reader);
        return "reader added";
    }
}
