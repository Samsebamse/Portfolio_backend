package com.portfolio.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Image {

    private final UUID imageId;
    private final UUID projectId;
    @NotBlank(message = "Path can not be empty!")
    private String path;
    private String dimensions;

    public Image(
            @JsonProperty("imageId") UUID imageId,
            @JsonProperty("projectId") UUID projectId,
            @JsonProperty("path") String path,
            @JsonProperty("dimensions") String dimensions
    ) {
        this.imageId = imageId;
        this.projectId = projectId;
        this.path = path;
        this.dimensions = dimensions;
    }

    public UUID getImageId() {
        return imageId;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public String getPath() {
        return path;
    }

    public String getDimensions() {
        return dimensions;
    }
}
