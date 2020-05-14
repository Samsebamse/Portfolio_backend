package com.portfolio.demo.dao;

import com.portfolio.demo.model.Author;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface AuthorDao {

    int addAuthor(UUID id, Author author);

    default int addAuthor(Author author){
        UUID id = UUID.randomUUID();
        return addAuthor(id, author);
    }

    Optional<Author> getAuthor();

    int deleteAuthor();

    int updateAuthor(Map<String, String> keyValuePair);

}
