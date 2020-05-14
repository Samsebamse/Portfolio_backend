package com.portfolio.demo.service;

import com.portfolio.demo.dao.AuthorDao;
import com.portfolio.demo.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Service
public class AuthorService {

    private final AuthorDao authorDao;

    @Autowired
    public AuthorService(@Qualifier("author") AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public int addAuthor(Author author) {
        return authorDao.addAuthor(author);
    }

    public Optional<Author> getAuthor() {
        return authorDao.getAuthor();
    }

    public int deleteAuthor() {
        return authorDao.deleteAuthor();
    }

    public int updateAuthor(Map<String, String> keyValuePair) {
        return authorDao.updateAuthor(keyValuePair);
    }
}
