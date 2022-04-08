package org.egorkazantsev.library.mapper.converter;

import org.jooq.Converter;
import org.jooq.types.YearToSecond;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.Period;

@Component
public class PeriodConverter implements Converter<YearToSecond, Integer> {

    @Override
    public Integer from(YearToSecond yearToSecond) {
        return yearToSecond.getDays();
    }

    @Override
    public YearToSecond to(Integer days) {
        return YearToSecond.valueOf(Period.ofDays(days));
    }

    @Override
    @NonNull
    public Class<YearToSecond> fromType() {
        return YearToSecond.class;
    }

    @Override
    @NonNull
    public Class<Integer> toType() {
        return Integer.class;
    }

}
