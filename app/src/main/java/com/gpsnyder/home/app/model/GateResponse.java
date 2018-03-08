package com.gpsnyder.home.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GateResponse {

    private boolean successful;
    private String timestamp;

    @JsonProperty
    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    @JsonProperty
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
