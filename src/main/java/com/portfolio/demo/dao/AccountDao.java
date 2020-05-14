package com.portfolio.demo.dao;

import com.portfolio.demo.model.Account;
import com.portfolio.demo.model.Account_Authority;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface AccountDao {

    int addAccount(UUID id, Account account);

    default int addAccount(Account account) {
        UUID id = UUID.randomUUID();
        return addAccount(id, account);
    }

    int addAuthorityToAccount(UUID accountId, UUID authorityId);

    int deleteAuthorityToAccount(UUID accountId, UUID authorityId);

    List<Account_Authority> getAllAccounts();

    Optional<Account_Authority> getAccountById(UUID id);

    int deleteAccountById(UUID id);

    int updateAccountById(UUID id, Map<String, String> keyValuePair);

}
