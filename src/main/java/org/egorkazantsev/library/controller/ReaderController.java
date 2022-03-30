package org.egorkazantsev.library.controller;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.jooq.generated.tables.pojos.Reader;
import org.egorkazantsev.library.service.ReaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reader")
public class ReaderController {

    private final ReaderService readerService;

    @GetMapping("/all")
    public List<Reader> getAllReaders() {
        return readerService.getAllReaders();
    }

    @PostMapping("/add")
    public String addReader(@RequestBody Reader reader) {
        return readerService.addReader(reader);
    }
}
