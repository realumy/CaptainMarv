package com.example.alainbansais.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultContainer<T> {
    private final List<T> list;

    @JsonCreator
    public ResultContainer(@JsonProperty("results") List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }
}
