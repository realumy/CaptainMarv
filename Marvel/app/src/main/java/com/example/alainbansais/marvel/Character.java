package com.example.alainbansais.marvel;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Character implements Parcelable {
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

    protected Character(Parcel in) {
        id = in.readString();
        nickname = in.readString();
        description = in.readString();
        characterResource = in.readParcelable(CharacterResource.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nickname);
        dest.writeString(description);
        dest.writeParcelable(characterResource, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

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

    public String getSmallSize() {
        return characterResource.getPath() + "/standard_medium." + characterResource.getExt();
    }

    public String getBigSize(){
        return characterResource.getPath() + "/standard_fantastic." + characterResource.getExt();
    }
}
