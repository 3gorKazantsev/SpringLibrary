/*
 * This file is generated by jOOQ.
 */
package org.egorkazantsev.library.repository.generated.jooq.tables;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.egorkazantsev.library.repository.generated.jooq.Keys;
import org.egorkazantsev.library.repository.generated.jooq.Public;
import org.egorkazantsev.library.repository.generated.jooq.tables.records.BookOrderRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.types.YearToSecond;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BookOrder extends TableImpl<BookOrderRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.book_order</code>
     */
    public static final BookOrder BOOK_ORDER = new BookOrder();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BookOrderRecord> getRecordType() {
        return BookOrderRecord.class;
    }

    /**
     * The column <code>public.book_order.id</code>.
     */
    public final TableField<BookOrderRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("uuid_generate_v4()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.book_order.reader_id</code>.
     */
    public final TableField<BookOrderRecord, UUID> READER_ID = createField(DSL.name("reader_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.book_order.book_id</code>.
     */
    public final TableField<BookOrderRecord, UUID> BOOK_ID = createField(DSL.name("book_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.book_order.borrowing_date</code>.
     */
    public final TableField<BookOrderRecord, LocalDate> BORROWING_DATE = createField(DSL.name("borrowing_date"), SQLDataType.LOCALDATE.nullable(false), this, "");

    /**
     * The column <code>public.book_order.period</code>.
     */
    public final TableField<BookOrderRecord, YearToSecond> PERIOD = createField(DSL.name("period"), SQLDataType.INTERVAL.nullable(false), this, "");

    private BookOrder(Name alias, Table<BookOrderRecord> aliased) {
        this(alias, aliased, null);
    }

    private BookOrder(Name alias, Table<BookOrderRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.book_order</code> table reference
     */
    public BookOrder(String alias) {
        this(DSL.name(alias), BOOK_ORDER);
    }

    /**
     * Create an aliased <code>public.book_order</code> table reference
     */
    public BookOrder(Name alias) {
        this(alias, BOOK_ORDER);
    }

    /**
     * Create a <code>public.book_order</code> table reference
     */
    public BookOrder() {
        this(DSL.name("book_order"), null);
    }

    public <O extends Record> BookOrder(Table<O> child, ForeignKey<O, BookOrderRecord> key) {
        super(child, key, BOOK_ORDER);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<BookOrderRecord> getPrimaryKey() {
        return Keys.BOOK_ORDER_PKEY;
    }

    @Override
    public List<ForeignKey<BookOrderRecord, ?>> getReferences() {
        return Arrays.asList(Keys.BOOK_ORDER__BOOK_ORDER_READER_ID_FKEY, Keys.BOOK_ORDER__BOOK_ORDER_BOOK_ID_FKEY);
    }

    private transient Reader _reader;
    private transient Book _book;

    /**
     * Get the implicit join path to the <code>public.reader</code> table.
     */
    public Reader reader() {
        if (_reader == null)
            _reader = new Reader(this, Keys.BOOK_ORDER__BOOK_ORDER_READER_ID_FKEY);

        return _reader;
    }

    /**
     * Get the implicit join path to the <code>public.book</code> table.
     */
    public Book book() {
        if (_book == null)
            _book = new Book(this, Keys.BOOK_ORDER__BOOK_ORDER_BOOK_ID_FKEY);

        return _book;
    }

    @Override
    public BookOrder as(String alias) {
        return new BookOrder(DSL.name(alias), this);
    }

    @Override
    public BookOrder as(Name alias) {
        return new BookOrder(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public BookOrder rename(String name) {
        return new BookOrder(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BookOrder rename(Name name) {
        return new BookOrder(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<UUID, UUID, UUID, LocalDate, YearToSecond> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
