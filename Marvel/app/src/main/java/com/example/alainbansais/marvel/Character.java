package com.example.alainbansais.marvel;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {
    private String id;
    private String nickname;
    private String description;

    @JsonCreator
    public Character(
            @JsonProperty("id") String id,
            @JsonProperty("name") String nickname,
            @JsonProperty("description") String description

    ) {
        super();
        this.id = id;
        this.nickname = nickname;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
