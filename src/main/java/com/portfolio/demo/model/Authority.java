package com.portfolio.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Authority {

    private final UUID authorityId;
    @NotBlank(message = "Authority type can not be empty!")
    private final AuthorityType type;

    public enum AuthorityType {
        USER, MODERATOR, ADMIN
    }

    public Authority(@JsonProperty("authorityId") UUID authorityId, @JsonProperty("type") AuthorityType type) {
        this.authorityId = authorityId;
        this.type = type;
    }

    public UUID getAuthorityId() {
        return authorityId;
    }

    public AuthorityType getType() {
        return type;
    }

}
