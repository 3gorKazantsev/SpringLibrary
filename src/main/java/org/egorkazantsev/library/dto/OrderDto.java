package org.egorkazantsev.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Reader;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderDto {

    private UUID id;
    private Reader reader;
    private BookForOrderDto book;
    private LocalDate borrowingDate;
    private Integer period;

}
