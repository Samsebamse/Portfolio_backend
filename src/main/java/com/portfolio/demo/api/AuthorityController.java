package com.portfolio.demo.api;

import com.portfolio.demo.model.Authority;
import com.portfolio.demo.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;

    @Autowired
    public AuthorityController(AuthorityService authorityService){
        this.authorityService = authorityService;
    }

    @PostMapping
    public void addAuthority(@Valid @Nonnull @RequestBody Authority authority) {
        authorityService.addAuthority(authority);
    }

    @GetMapping
    public List<Authority> getAllAuthorities() {
        return authorityService.getAllAuthorities();
    }

    @DeleteMapping(path = "{id}")
    public void deleteAuthorityById(@PathVariable("id") UUID id) {
        authorityService.deleteAuthorityById(id);
    }
}
