package com.portfolio.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Account_Authority {

    private final UUID authorityId;
    private final UUID accountId;
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    Authority.AuthorityType authorityType;

    public Account_Authority(
            @JsonProperty("authorityId") UUID authorityId,
            @JsonProperty("accountId") UUID accountId,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("email") String email,
            @JsonProperty("enabled") boolean enabled,
            @JsonProperty("type") Authority.AuthorityType authorityType
    ) {
        this.authorityId = authorityId;
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.authorityType = authorityType;
    }

    public UUID getAuthorityId() {
        return authorityId;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Authority.AuthorityType getAuthorityType() {
        return authorityType;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
