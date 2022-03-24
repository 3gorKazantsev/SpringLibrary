package org.egorkazantsev.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Period;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private UUID id;
    private Reader reader;
    private Book book;
    private Date borrowDate;
    private Period term;

}
