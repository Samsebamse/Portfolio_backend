package com.portfolio.demo.service;

import com.portfolio.demo.dao.AuthorityDao;
import com.portfolio.demo.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorityService {

    private final AuthorityDao authorityDao;

    @Autowired
    public AuthorityService(@Qualifier("authority") AuthorityDao authorityDao) {
        this.authorityDao = authorityDao;
    }

    public int addAuthority(Authority authority) {
        return authorityDao.addAuthority(authority);
    }

    public List<Authority> getAllAuthorities() {
        return authorityDao.getAllAuthorities();
    }

    public int deleteAuthorityById(UUID id) {
        return authorityDao.deleteAuthorityById(id);
    }
}
