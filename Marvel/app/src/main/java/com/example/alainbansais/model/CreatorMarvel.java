package com.example.alainbansais.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatorMarvel implements Parcelable {
    private String id;
    private String firstName;

    @JsonCreator
    public CreatorMarvel(
            @JsonProperty("id") String id,
            @JsonProperty("firstName") String firstName
    ) {
        super();
        this.id = id;
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getId() {
        return id;
    }

    protected CreatorMarvel(Parcel in) {
        id = in.readString();
        firstName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(firstName);
    }

    public static final Creator<CreatorMarvel> CREATOR = new Creator<CreatorMarvel>() {
        @Override
        public CreatorMarvel createFromParcel(Parcel in) {
            return new CreatorMarvel(in);
        }

        @Override
        public CreatorMarvel[] newArray(int size) {
            return new CreatorMarvel[size];
        }
    };
}
