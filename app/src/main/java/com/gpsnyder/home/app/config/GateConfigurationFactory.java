package com.gpsnyder.home.app.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class GateConfigurationFactory {

    @NotNull
    private String pin;

    @NotNull
    private long duration;

    @JsonProperty
    public String getPin() {
        return pin;
    }

    @JsonProperty
    public void setPin(String pin) {
        this.pin = pin;
    }

    @JsonProperty
    public long getDuration() {
        return duration;
    }

    @JsonProperty
    public void setDuration(long duration) {
        this.duration = duration;
    }
}
