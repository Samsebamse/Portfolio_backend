package com.portfolio.demo.dao;

import com.portfolio.demo.model.Authority;

import java.util.List;
import java.util.UUID;

public interface AuthorityDao {

    int addAuthority(UUID id, Authority authority);

    default int addAuthority(Authority authority) {
        UUID id = UUID.randomUUID();
        return addAuthority(id, authority);
    }

    List<Authority> getAllAuthorities();

    int deleteAuthorityById(UUID id);
}
