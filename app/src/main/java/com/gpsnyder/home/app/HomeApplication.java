package com.gpsnyder.home.app;

import com.gpsnyder.home.app.config.ConsoleConfiguration;
import com.gpsnyder.home.app.config.GateConfigurationFactory;
import com.gpsnyder.home.app.resource.GateResource;
import com.gpsnyder.home.app.resource.HomeResource;
import com.gpsnyder.home.gate.GateBuzzer;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.eclipse.jetty.server.session.SessionHandler;
import org.secnod.dropwizard.shiro.ShiroBundle;
import org.secnod.dropwizard.shiro.ShiroConfiguration;

public class HomeApplication extends Application<ConsoleConfiguration> {

    public static void main(String[] args) throws Exception {
        new HomeApplication().run(args);
    }

    private final ShiroBundle<ConsoleConfiguration> shiro = new ShiroBundle<ConsoleConfiguration>() {

        @Override
        protected ShiroConfiguration narrow(ConsoleConfiguration consoleConfiguration) {
            return consoleConfiguration.getShiroConfiguration();
        }
    };

    @Override
    public String getName() {
        return "home-pi-app";
    }

    @Override
    public void initialize(Bootstrap<ConsoleConfiguration> bootstrap) {
        bootstrap.addBundle(shiro);
        bootstrap.addBundle(new ViewBundle<>());
        bootstrap.addBundle(new AssetsBundle("/assets"));

    }

    @Override
    public void run(ConsoleConfiguration configuration, Environment environment) {
        environment.getApplicationContext().setSessionHandler(new SessionHandler());
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