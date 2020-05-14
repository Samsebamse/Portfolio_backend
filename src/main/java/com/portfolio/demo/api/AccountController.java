package com.portfolio.demo.api;

import com.portfolio.demo.model.Account;
import com.portfolio.demo.model.Account_Authority;
import com.portfolio.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public void addAccount(@Valid @Nonnull @RequestBody Account account) {
        accountService.addAccount(account);
    }

    @PostMapping(path = "{id}/authorities")
    public void addAuthorityToAccount(@PathVariable("id") UUID accountId, @Valid @Nonnull @RequestBody Map<String, UUID> authorityId) {
        UUID authId = authorityId.entrySet().stream().map(item -> item.getValue()).findFirst().get();
        accountService.addAuthorityToAccount(accountId, authId);
    }

    @DeleteMapping(path = "{id}/authorities")
    public void deleteAuthorityToAccount(@PathVariable("id") UUID accountId, @Valid @Nonnull @RequestBody Map<String, UUID> authorityId) {
        UUID authId = authorityId.entrySet().stream().map(item -> item.getValue()).findFirst().get();
        accountService.deleteAuthorityToAccount(accountId, authId);
    }

    @GetMapping
    public List<Account_Authority> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping(path = "{id}")
    public Optional<Account_Authority> getAccountById(@PathVariable("id") UUID id) {
        return accountService.getAccountById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteAccountById(@PathVariable("id") UUID id) {
        accountService.deleteAccountById(id);
    }

    @PatchMapping(path = "{id}")
    public void updateAccountById(
            @PathVariable("id") UUID id,
            @Valid @Nonnull @RequestBody Map<String, String> keyValuePair) {
        accountService.updateAccountById(id, keyValuePair);
    }
}
