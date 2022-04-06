package org.egorkazantsev.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AuthorDto {

    private UUID id;
    private String fullName;

}
