package com.example.alainbansais.marvel;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterResource {
    private String path;
    private String ext;

    @JsonCreator
    public CharacterResource(
            @JsonProperty("path") String path,
            @JsonProperty("extension") String ext
    ) {
        super();
        this.path = path;
        this.ext = ext;
    }

    public String getPath() {
        return path;
    }

    public String getExt() {
        return ext;
    }

}
