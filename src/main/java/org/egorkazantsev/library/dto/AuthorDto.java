package org.egorkazantsev.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.egorkazantsev.library.jooq.generated.tables.records.AuthorRecord;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AuthorDto {

    private UUID id;
    private String fullName;

    public AuthorDto(AuthorRecord author) {
        this.id = author.getId();
        this.fullName = author.getFullName();
    }
}
