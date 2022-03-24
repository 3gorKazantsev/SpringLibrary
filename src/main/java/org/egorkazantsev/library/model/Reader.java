package org.egorkazantsev.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reader {

    private UUID id;
    private String fullName;
    private String address;
    private String contacts;
}
