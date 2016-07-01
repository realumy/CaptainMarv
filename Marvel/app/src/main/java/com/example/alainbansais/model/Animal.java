package com.example.alainbansais.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Animal implements Parcelable{

    static Builder builder(){
        return new AutoValue_Animal.Builder();
    }
    abstract String getName();
    abstract int getNumberOfLegs();
    abstract boolean getIsFish();

    @AutoValue.Builder
    public abstract static class Builder {
        abstract Builder setName(String name);
        abstract Builder setNumberOfLegs(int numberOfLegs);
        abstract Builder setIsFish(boolean isFish);

        abstract Animal build();

    }

}
