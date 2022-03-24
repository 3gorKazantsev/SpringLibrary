package org.egorkazantsev.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private UUID id;
    private Author author;
    private String title;
    private String description;
    private String genre;
    private Integer copiesNum;
}
