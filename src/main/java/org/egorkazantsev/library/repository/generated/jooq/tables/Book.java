/*
 * This file is generated by jOOQ.
 */
package org.egorkazantsev.library.repository.generated.jooq.tables;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.egorkazantsev.library.repository.generated.jooq.Keys;
import org.egorkazantsev.library.repository.generated.jooq.Public;
import org.egorkazantsev.library.repository.generated.jooq.tables.records.BookRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Book extends TableImpl<BookRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.book</code>
     */
    public static final Book BOOK = new Book();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BookRecord> getRecordType() {
        return BookRecord.class;
    }

    /**
     * The column <code>public.book.id</code>.
     */
    public final TableField<BookRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("uuid_generate_v4()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.book.title</code>.
     */
    public final TableField<BookRecord, String> TITLE = createField(DSL.name("title"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.book.author_id</code>.
     */
    public final TableField<BookRecord, UUID> AUTHOR_ID = createField(DSL.name("author_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.book.description</code>.
     */
    public final TableField<BookRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.book.genre</code>.
     */
    public final TableField<BookRecord, String> GENRE = createField(DSL.name("genre"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.book.stock</code>.
     */
    public final TableField<BookRecord, Integer> STOCK = createField(DSL.name("stock"), SQLDataType.INTEGER.nullable(false), this, "");

    private Book(Name alias, Table<BookRecord> aliased) {
        this(alias, aliased, null);
    }

    private Book(Name alias, Table<BookRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.book</code> table reference
     */
    public Book(String alias) {
        this(DSL.name(alias), BOOK);
    }

    /**
     * Create an aliased <code>public.book</code> table reference
     */
    public Book(Name alias) {
        this(alias, BOOK);
    }

    /**
     * Create a <code>public.book</code> table reference
     */
    public Book() {
        this(DSL.name("book"), null);
    }

    public <O extends Record> Book(Table<O> child, ForeignKey<O, BookRecord> key) {
        super(child, key, BOOK);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<BookRecord> getPrimaryKey() {
        return Keys.BOOK_PKEY;
    }

    @Override
    public List<ForeignKey<BookRecord, ?>> getReferences() {
        return Arrays.asList(Keys.BOOK__BOOK_AUTHOR_ID_FKEY);
    }

    private transient Author _author;

    /**
     * Get the implicit join path to the <code>public.author</code> table.
     */
    public Author author() {
        if (_author == null)
            _author = new Author(this, Keys.BOOK__BOOK_AUTHOR_ID_FKEY);

        return _author;
    }

    @Override
    public Book as(String alias) {
        return new Book(DSL.name(alias), this);
    }

    @Override
    public Book as(Name alias) {
        return new Book(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Book rename(String name) {
        return new Book(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Book rename(Name name) {
        return new Book(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<UUID, String, UUID, String, String, Integer> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
