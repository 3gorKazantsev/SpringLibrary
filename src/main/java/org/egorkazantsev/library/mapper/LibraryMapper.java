package org.egorkazantsev.library.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.egorkazantsev.library.dto.AuthorDto;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Author;
import org.springframework.stereotype.Component;

@Component
public class LibraryMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Author.class, AuthorDto.class)
                .byDefault()
                .register();
    }
}
