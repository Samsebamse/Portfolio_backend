package com.portfolio.demo.dao;

import com.portfolio.demo.model.Account;
import com.portfolio.demo.model.Account_Authority;
import com.portfolio.demo.model.Authority;
import com.portfolio.demo.util.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("account")
public class AccountDaoAccessService implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDaoAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addAccount(UUID id, Account account) {
        final String sql = "INSERT INTO account (accountid, username, password, email, enabled) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                id,
                account.getUsername(),
                account.getPassword(),
                account.getEmail(),
                account.isEnabled()
        );
    }

    @Override
    public int addAuthorityToAccount(UUID accountId, UUID authorityId) {
        final String sql = "INSERT INTO account_authority (accountid, authorityid) VALUES (?, ?)";
        return jdbcTemplate.update(sql, accountId, authorityId);
    }

    @Override
    public int deleteAuthorityToAccount(UUID accountId, UUID authorityId) {
        final String sql = "DELETE FROM account_authority WHERE accountid = ? AND authorityid = ?";
        return jdbcTemplate.update(sql, accountId, authorityId);
    }

    @Override
    public List<Account_Authority> getAllAccounts() {
        final String sql = "SELECT * FROM account LEFT JOIN account_authority USING (accountid) LEFT JOIN authority USING (authorityid)";
        List<Account_Authority> allAccountsFullDetails = jdbcTemplate.query(sql, ((resultSet, i) -> {
            UUID accountID = UUID.fromString(resultSet.getString("accountid"));
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            boolean enabled = resultSet.getBoolean("enabled");

            String authId = resultSet.getString("authorityid");
            if(authId != null) {
                UUID authorityId = UUID.fromString(authId);
                Authority.AuthorityType authorityType =
                        Authority.AuthorityType.valueOf(resultSet.getString("type").toUpperCase());
                return new Account_Authority(authorityId, accountID, username, password, email, enabled, authorityType);
            }
            return new Account_Authority(null, accountID, username, password, email, enabled, null);
        }));
        return allAccountsFullDetails;
    }

    @Override
    public Optional<Account_Authority> getAccountById(UUID accountId) {
        final String sql = "SELECT * FROM account LEFT JOIN account_authority USING (accountid) LEFT JOIN authority USING (authorityid) WHERE accountid = ?";
        try {
            Account_Authority account = jdbcTemplate.queryForObject(sql, new Object[]{accountId}, (resultSet, i) -> {
                UUID id = UUID.fromString(resultSet.getString("accountid"));
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                boolean enabled = resultSet.getBoolean("enabled");
                String authId = resultSet.getString("authorityid");
                if(authId != null) {
                    UUID authorityId = UUID.fromString(authId);
                    Authority.AuthorityType authorityType = Authority.AuthorityType.valueOf(resultSet.getString("type").toUpperCase());
                    return new Account_Authority(authorityId, id, username, password, email, enabled, authorityType);
                }
                    return new Account_Authority(null, id, username, password, email, enabled, null);
            });
            return Optional.ofNullable(account);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public int deleteAccountById(UUID accountId) {
        final String sql = "DELETE FROM account WHERE accountid = ?";
        return jdbcTemplate.update(sql, accountId);
    }

    @Override
    public int updateAccountById(UUID accountId, Map<String, String> keyValuePair) {
        StringJoiner stringJoiner = new StringJoiner("", "UPDATE account SET ", " WHERE accountid = ?");
        final String sql =  stringJoiner.add(HelperFunctions.createPATCHsql(keyValuePair)).toString();
        return jdbcTemplate.update(sql, accountId);
    }
}
