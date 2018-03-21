package com.gpsnyder.home.app.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.secnod.dropwizard.shiro.ShiroConfiguration;

import javax.validation.constraints.NotNull;

public class ConsoleConfiguration extends Configuration {

    @NotNull
    private GateConfigurationFactory gateConfigurationFactory;

    @NotNull
    private ShiroConfiguration shiroConfiguration;

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

    @JsonProperty("shiro")
    public ShiroConfiguration getShiroConfiguration() {
        return shiroConfiguration;
    }

    @JsonProperty("shiro")
    public void setShiroConfiguration(ShiroConfiguration shiroConfiguration) {
        this.shiroConfiguration = shiroConfiguration;
    }
}
