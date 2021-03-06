/*
 * This file is generated by jOOQ.
 */
package org.egorkazantsev.library.repository.generated.jooq.tables.daos;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.egorkazantsev.library.repository.generated.jooq.tables.Reader;
import org.egorkazantsev.library.repository.generated.jooq.tables.records.ReaderRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ReaderDao extends DAOImpl<ReaderRecord, org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader, UUID> {

    /**
     * Create a new ReaderDao without any configuration
     */
    public ReaderDao() {
        super(Reader.READER, org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader.class);
    }

    /**
     * Create a new ReaderDao with an attached configuration
     */
    public ReaderDao(Configuration configuration) {
        super(Reader.READER, org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader.class, configuration);
    }

    @Override
    public UUID getId(org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(Reader.READER.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader> fetchById(UUID... values) {
        return fetch(Reader.READER.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader fetchOneById(UUID value) {
        return fetchOne(Reader.READER.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader> fetchOptionalById(UUID value) {
        return fetchOptional(Reader.READER.ID, value);
    }

    /**
     * Fetch records that have <code>full_name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader> fetchRangeOfFullName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Reader.READER.FULL_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>full_name IN (values)</code>
     */
    public List<org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader> fetchByFullName(String... values) {
        return fetch(Reader.READER.FULL_NAME, values);
    }

    /**
     * Fetch records that have <code>address BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader> fetchRangeOfAddress(String lowerInclusive, String upperInclusive) {
        return fetchRange(Reader.READER.ADDRESS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>address IN (values)</code>
     */
    public List<org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader> fetchByAddress(String... values) {
        return fetch(Reader.READER.ADDRESS, values);
    }

    /**
     * Fetch records that have <code>contacts BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader> fetchRangeOfContacts(String lowerInclusive, String upperInclusive) {
        return fetchRange(Reader.READER.CONTACTS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>contacts IN (values)</code>
     */
    public List<org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Reader> fetchByContacts(String... values) {
        return fetch(Reader.READER.CONTACTS, values);
    }
}
