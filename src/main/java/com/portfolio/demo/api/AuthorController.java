package com.portfolio.demo.api;

import com.portfolio.demo.model.Author;
import com.portfolio.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.validation.Valid;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public void addAuthor(@Valid @Nonnull @RequestBody Author author) {
        authorService.addAuthor(author);
    }

    @GetMapping
    public Optional<Author> getAuthor() {
        return authorService.getAuthor();
    }

    @DeleteMapping
    public void deleteAuthor() {
        authorService.deleteAuthor();
    }

    @PatchMapping()
    public void updateAuthor(@Valid @Nonnull @RequestBody Map<String, String> keyValuePair) {
        authorService.updateAuthor(keyValuePair);
    }
}
