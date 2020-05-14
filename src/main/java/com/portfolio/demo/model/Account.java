package com.portfolio.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Account {

    private final UUID accountId;
    @NotBlank(message = "Username can not be empty!")
    private String username;
    @NotBlank(message = "Password can not be empty!")
    private String password;
    private String email;
    private boolean enabled;

    public Account(
            @JsonProperty("accountId") UUID accountId,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("email") String email,
            @JsonProperty("enabled") boolean enabled
    ) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean setEnabled) {
        this.enabled = setEnabled;
    }
}
