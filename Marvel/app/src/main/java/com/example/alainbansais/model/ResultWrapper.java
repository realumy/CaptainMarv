package com.example.alainbansais.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultWrapper<T> {
    private final ResultContainer<T> resultContainer;

    @JsonCreator
    public ResultWrapper(@JsonProperty("data") ResultContainer<T> resultContainer) {
        this.resultContainer = resultContainer;
    }

    public ResultContainer<T> getResultContainer() {
        return resultContainer;
    }
}
