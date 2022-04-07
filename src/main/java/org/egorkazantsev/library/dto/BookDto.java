package org.egorkazantsev.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private UUID id;
    private String title;
    private AuthorDto author;
    private String description;
    private String genre;
    private Integer stock;

}
