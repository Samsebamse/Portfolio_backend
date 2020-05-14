package com.portfolio.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.UUID;

public class Author {

    private final UUID authorId;
    private String name;
    private String title;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;


    public Author(
            @JsonProperty("authorId") UUID authorId,
            @JsonProperty("name") String name,
            @JsonProperty("title") String title,
            @JsonProperty("field1") String field1,
            @JsonProperty("field2") String field2,
            @JsonProperty("field3") String field3,
            @JsonProperty("field4") String field4,
            @JsonProperty("field5") String field5
     ) {
        this.authorId = authorId;
        this.name = name;
        this.title = title;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.field5 = field5;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    public String getField4() {
        return field4;
    }

    public String getField5() {
        return field5;
    }

}
