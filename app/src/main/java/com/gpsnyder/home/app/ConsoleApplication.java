package com.gpsnyder.home.app;

import com.gpsnyder.home.app.config.ConsoleConfiguration;
import com.gpsnyder.home.app.config.GateConfigurationFactory;
import com.gpsnyder.home.app.resource.GateResource;
import com.gpsnyder.home.app.resource.HomeResource;
import com.gpsnyder.home.gate.GateBuzzer;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ConsoleApplication extends Application<ConsoleConfiguration> {

    public static void main(String[] args) throws Exception {
        new ConsoleApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<ConsoleConfiguration> bootstrap) {
    }

    @Override
    public void run(ConsoleConfiguration configuration, Environment environment) {
        final GateBuzzer.Options options = new GateBuzzer.Options();
        final GateConfigurationFactory gateConfigurationFactory = configuration.getGateConfigurationFactory();
        final Pin pin = RaspiPin.getPinByName(gateConfigurationFactory.getPin());
        if (pin != null) {
            options.setGatePin(pin);
        }
        options.setBuzzDuration(gateConfigurationFactory.getDuration());
        GateBuzzer.initialize(options);
        final HomeResource homeResource = new HomeResource();
        environment.jersey().register(homeResource);
        final GateResource gateResource = new GateResource();
        environment.jersey().register(gateResource);
    }

}