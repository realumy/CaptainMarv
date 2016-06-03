package com.example.alainbansais.marvel;

import org.codehaus.jackson.annotate.JsonProperty;

public class StateRequest {
    private String code;
    private String status;
    private String data;

    public StateRequest(
            @JsonProperty("code") String code,
            @JsonProperty("status") String status,
            @JsonProperty("data") String data
    ) {
        super();
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
