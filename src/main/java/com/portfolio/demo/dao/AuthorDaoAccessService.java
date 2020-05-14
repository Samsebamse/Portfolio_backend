package com.portfolio.demo.dao;

import com.portfolio.demo.model.Author;
import com.portfolio.demo.util.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.UUID;

@Repository("author")
public class AuthorDaoAccessService implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addAuthor(UUID id, Author author) {
        final String sql =
                "INSERT INTO " +
                "author (authorid, name, title, field1, field2, field3, field4, field5) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                id,
                author.getName(),
                author.getTitle(),
                author.getField1(),
                author.getField2(),
                author.getField3(),
                author.getField4(),
                author.getField5()
        );
    }

    @Override
    public Optional<Author> getAuthor() {
        final String sql = "SELECT * FROM author";
        Author author = jdbcTemplate.queryForObject(sql, new Object[]{}, (resultSet, i) -> {
            UUID authorId = UUID.fromString(resultSet.getString("authorid"));
            String name = resultSet.getString("name");
            String title = resultSet.getString("title");
            String field1 = resultSet.getString("field1");
            String field2 = resultSet.getString("field2");
            String field3 = resultSet.getString("field3");
            String field4 = resultSet.getString("field4");
            String field5 = resultSet.getString("field5");
            return new Author(authorId, name, title, field1, field2, field3, field4, field5);
        });
        return Optional.ofNullable(author);
    }

    @Override
    public int deleteAuthor() {
        final String sql = "DELETE FROM author";
        return jdbcTemplate.update(sql);
    }

    @Override
    public int updateAuthor(Map<String, String> keyValuePair) {
        StringJoiner stringJoiner = new StringJoiner("", "UPDATE author SET ", "");
        final String sql =  stringJoiner.add(HelperFunctions.createPATCHsql(keyValuePair)).toString();
        return jdbcTemplate.update(sql);
    }
}
