package com.portfolio.demo.dao;

import com.portfolio.demo.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("authority")
public class AuthorityAccessService implements AuthorityDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorityAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addAuthority(UUID id, Authority authority) {
        final String sql = "INSERT INTO authority (authorityid, type) VALUES (?, ?)";
        return jdbcTemplate.update(sql, id, authority.getType());
    }

    @Override
    public List<Authority> getAllAuthorities() {
        final String sql = "SELECT * FROM authority";
        List<Authority> allAuthorities = jdbcTemplate.query(sql, ((resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("authorityid"));
            Authority.AuthorityType type =
                    Authority.AuthorityType.valueOf(resultSet.getString("type").toUpperCase());
            return new Authority(id, type);
        }));
        return allAuthorities;
    }

    @Override
    public int deleteAuthorityById(UUID id) {
        final String sql = "DELETE FROM authority WHERE authorityid = ?";
        return jdbcTemplate.update(sql, id);
    }
}
