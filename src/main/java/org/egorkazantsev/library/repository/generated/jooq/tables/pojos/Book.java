/*
 * This file is generated by jOOQ.
 */
package org.egorkazantsev.library.repository.generated.jooq.tables.pojos;


import java.io.Serializable;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID    id;
    private String  title;
    private UUID    authorId;
    private String  description;
    private String  genre;
    private Integer stock;

    public Book() {}

    public Book(Book value) {
        this.id = value.id;
        this.title = value.title;
        this.authorId = value.authorId;
        this.description = value.description;
        this.genre = value.genre;
        this.stock = value.stock;
    }

    public Book(
        UUID    id,
        String  title,
        UUID    authorId,
        String  description,
        String  genre,
        Integer stock
    ) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.description = description;
        this.genre = genre;
        this.stock = stock;
    }

    /**
     * Getter for <code>public.book.id</code>.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.book.id</code>.
     */
    public Book setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.book.title</code>.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Setter for <code>public.book.title</code>.
     */
    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Getter for <code>public.book.author_id</code>.
     */
    public UUID getAuthorId() {
        return this.authorId;
    }

    /**
     * Setter for <code>public.book.author_id</code>.
     */
    public Book setAuthorId(UUID authorId) {
        this.authorId = authorId;
        return this;
    }

    /**
     * Getter for <code>public.book.description</code>.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>public.book.description</code>.
     */
    public Book setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Getter for <code>public.book.genre</code>.
     */
    public String getGenre() {
        return this.genre;
    }

    /**
     * Setter for <code>public.book.genre</code>.
     */
    public Book setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    /**
     * Getter for <code>public.book.stock</code>.
     */
    public Integer getStock() {
        return this.stock;
    }

    /**
     * Setter for <code>public.book.stock</code>.
     */
    public Book setStock(Integer stock) {
        this.stock = stock;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Book other = (Book) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        }
        else if (!title.equals(other.title))
            return false;
        if (authorId == null) {
            if (other.authorId != null)
                return false;
        }
        else if (!authorId.equals(other.authorId))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        }
        else if (!description.equals(other.description))
            return false;
        if (genre == null) {
            if (other.genre != null)
                return false;
        }
        else if (!genre.equals(other.genre))
            return false;
        if (stock == null) {
            if (other.stock != null)
                return false;
        }
        else if (!stock.equals(other.stock))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
        result = prime * result + ((this.authorId == null) ? 0 : this.authorId.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.genre == null) ? 0 : this.genre.hashCode());
        result = prime * result + ((this.stock == null) ? 0 : this.stock.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Book (");

        sb.append(id);
        sb.append(", ").append(title);
        sb.append(", ").append(authorId);
        sb.append(", ").append(description);
        sb.append(", ").append(genre);
        sb.append(", ").append(stock);

        sb.append(")");
        return sb.toString();
    }
}