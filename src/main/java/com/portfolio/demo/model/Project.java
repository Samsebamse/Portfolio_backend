package com.portfolio.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class Project {

    private final UUID projectId;
    @NotBlank(message = "Title can not be empty")
    private String title;
    private String description;
    private Optional<List<Image>> images;

    public Project (
            @JsonProperty("projectId") UUID projectId,
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("images") List<Image> images
    ) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.images = Optional.ofNullable(images);
    }

    public UUID getProjectId(){
        return projectId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Optional<List<Image>> getImages() {
        return images;
    }
}