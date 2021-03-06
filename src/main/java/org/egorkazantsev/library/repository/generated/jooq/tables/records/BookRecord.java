/*
 * This file is generated by jOOQ.
 */
package org.egorkazantsev.library.repository.generated.jooq.tables.records;


import java.util.UUID;

import org.egorkazantsev.library.repository.generated.jooq.tables.Book;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BookRecord extends UpdatableRecordImpl<BookRecord> implements Record6<UUID, String, UUID, String, String, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.book.id</code>.
     */
    public BookRecord setId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.book.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.book.title</code>.
     */
    public BookRecord setTitle(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.book.title</code>.
     */
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.book.author_id</code>.
     */
    public BookRecord setAuthorId(UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.book.author_id</code>.
     */
    public UUID getAuthorId() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>public.book.description</code>.
     */
    public BookRecord setDescription(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.book.description</code>.
     */
    public String getDescription() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.book.genre</code>.
     */
    public BookRecord setGenre(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.book.genre</code>.
     */
    public String getGenre() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.book.stock</code>.
     */
    public BookRecord setStock(Integer value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.book.stock</code>.
     */
    public Integer getStock() {
        return (Integer) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<UUID, String, UUID, String, String, Integer> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<UUID, String, UUID, String, String, Integer> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Book.BOOK.ID;
    }

    @Override
    public Field<String> field2() {
        return Book.BOOK.TITLE;
    }

    @Override
    public Field<UUID> field3() {
        return Book.BOOK.AUTHOR_ID;
    }

    @Override
    public Field<String> field4() {
        return Book.BOOK.DESCRIPTION;
    }

    @Override
    public Field<String> field5() {
        return Book.BOOK.GENRE;
    }

    @Override
    public Field<Integer> field6() {
        return Book.BOOK.STOCK;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getTitle();
    }

    @Override
    public UUID component3() {
        return getAuthorId();
    }

    @Override
    public String component4() {
        return getDescription();
    }

    @Override
    public String component5() {
        return getGenre();
    }

    @Override
    public Integer component6() {
        return getStock();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getTitle();
    }

    @Override
    public UUID value3() {
        return getAuthorId();
    }

    @Override
    public String value4() {
        return getDescription();
    }

    @Override
    public String value5() {
        return getGenre();
    }

    @Override
    public Integer value6() {
        return getStock();
    }

    @Override
    public BookRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public BookRecord value2(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public BookRecord value3(UUID value) {
        setAuthorId(value);
        return this;
    }

    @Override
    public BookRecord value4(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public BookRecord value5(String value) {
        setGenre(value);
        return this;
    }

    @Override
    public BookRecord value6(Integer value) {
        setStock(value);
        return this;
    }

    @Override
    public BookRecord values(UUID value1, String value2, UUID value3, String value4, String value5, Integer value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BookRecord
     */
    public BookRecord() {
        super(Book.BOOK);
    }

    /**
     * Create a detached, initialised BookRecord
     */
    public BookRecord(UUID id, String title, UUID authorId, String description, String genre, Integer stock) {
        super(Book.BOOK);

        setId(id);
        setTitle(title);
        setAuthorId(authorId);
        setDescription(description);
        setGenre(genre);
        setStock(stock);
    }

    /**
     * Create a detached, initialised BookRecord
     */
    public BookRecord(org.egorkazantsev.library.repository.generated.jooq.tables.pojos.Book value) {
        super(Book.BOOK);

        if (value != null) {
            setId(value.getId());
            setTitle(value.getTitle());
            setAuthorId(value.getAuthorId());
            setDescription(value.getDescription());
            setGenre(value.getGenre());
            setStock(value.getStock());
        }
    }
}
