package com.example.alainbansais.marvel;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {
    private String id;
    private String nickname;
    private String description;
    private CharacterResource characterResource;

    @JsonCreator
    public Character(
            @JsonProperty("id") String id,
            @JsonProperty("name") String nickname,
            @JsonProperty("description") String description,
            @JsonProperty("thumbnail") CharacterResource characterResource

    ) {
        super();
        this.id = id;
        this.nickname = nickname;
        this.description = description;
        this.characterResource = characterResource;
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

    public String getDescription() {
        return description;
    }

    public CharacterResource getCharacterResource() {
        return characterResource;
    }

    public String getSmallSize() {
        return characterResource.getPath() + "/standard_medium." + characterResource.getExt();
    }

    public String getBigSize(){
        return characterResource.getPath() + "/standard_fantastic." + characterResource.getExt();
    }
}
