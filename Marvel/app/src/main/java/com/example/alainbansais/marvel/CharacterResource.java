package com.example.alainbansais.marvel;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

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
