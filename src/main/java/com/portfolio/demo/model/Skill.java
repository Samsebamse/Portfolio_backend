package com.portfolio.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Skill {

    private UUID skillId;
    @NotBlank(message = "Skill name can not be empty!")
    private String name;
    @NotBlank(message = "Skill progress can not be empty!")
    private int progress;

    public Skill(
            @JsonProperty("skillId") UUID skillId,
            @JsonProperty("name") String name,
            @JsonProperty("progress") int progress
    ) {
        this.skillId = skillId;
        this.name = name;
        this.progress = progress;
    }

    public UUID getSkillId() {
        return skillId;
    }

    public String getName() {
        return name;
    }

    public int getProgress() {
        return progress;
    }
}
