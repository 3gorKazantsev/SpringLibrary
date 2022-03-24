package org.egorkazantsev.library.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private UUID id;
    private String fullName;
    private String biography;
}
