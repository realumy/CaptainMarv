package com.example.alainbansais.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterResource implements Parcelable {
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

    protected CharacterResource(Parcel in) {
        path = in.readString();
        ext = in.readString();
    }

    public static final Creator<CharacterResource> CREATOR = new Creator<CharacterResource>() {
        @Override
        public CharacterResource createFromParcel(Parcel in) {
            return new CharacterResource(in);
        }

        @Override
        public CharacterResource[] newArray(int size) {
            return new CharacterResource[size];
        }
    };

    public String getPath() {
        return path;
    }

    public String getExt() {
        return ext;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeString(ext);
    }
}
