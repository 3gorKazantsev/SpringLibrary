package org.egorkazantsev.library.controller;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.service.ReaderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/reader")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;


}
