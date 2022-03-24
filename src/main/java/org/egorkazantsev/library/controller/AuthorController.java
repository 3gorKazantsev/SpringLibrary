package org.egorkazantsev.library.controller;

import lombok.RequiredArgsConstructor;
import org.egorkazantsev.library.model.Author;
import org.egorkazantsev.library.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }
}
