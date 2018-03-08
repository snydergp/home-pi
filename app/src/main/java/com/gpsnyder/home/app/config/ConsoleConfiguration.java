package com.gpsnyder.home.app.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public class ConsoleConfiguration extends Configuration {

    @NotNull
    private GateConfigurationFactory gateConfigurationFactory;

    @JsonProperty("gate")
    public GateConfigurationFactory getGateConfigurationFactory() {
        if (gateConfigurationFactory == null) {
            gateConfigurationFactory = new GateConfigurationFactory();
        }
        return gateConfigurationFactory;
    }

    @JsonProperty("gate")
    public void setGateConfigurationFactory(GateConfigurationFactory gateConfigurationFactory) {
        this.gateConfigurationFactory = gateConfigurationFactory;
    }
}
