package com.portfolio.demo.service;

import com.portfolio.demo.dao.AccountDao;
import com.portfolio.demo.model.Account;
import com.portfolio.demo.model.Account_Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountDao accountDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(@Qualifier("account") AccountDao accountDao, PasswordEncoder passwordEncoder) {
        this.accountDao = accountDao;
        this.passwordEncoder = passwordEncoder;
    }

    public int addAccount(Account account){
        account.setEnabled(true);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountDao.addAccount(account);
    }

    public int addAuthorityToAccount(UUID accountId, UUID authorityId) {
        return accountDao.addAuthorityToAccount(accountId, authorityId);
    }

    public int deleteAuthorityToAccount(UUID accountId, UUID authorityId) {
        return accountDao.deleteAuthorityToAccount(accountId, authorityId);
    }

    public List<Account_Authority> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    public Optional<Account_Authority> getAccountById(UUID id) {
        return accountDao.getAccountById(id);
    }

    public int deleteAccountById(UUID id) {
        return accountDao.deleteAccountById(id);
    }

    public int updateAccountById(UUID id, Map<String, String> keyValuePair) {
        return accountDao.updateAccountById(id, keyValuePair);
    }
}
