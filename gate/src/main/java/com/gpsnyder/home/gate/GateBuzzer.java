package com.gpsnyder.home.gate;

import com.pi4j.io.gpio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GateBuzzer {

    private static final Logger LOG = LoggerFactory.getLogger(GateBuzzer.class);

    public static class Options {

        private Pin gatePin = RaspiPin.GPIO_00;
        private long buzzDuration = 5000;

        public void setGatePin(Pin gatePin) {
            this.gatePin = gatePin;
        }

        public void setBuzzDuration(long buzzDuration) {
            this.buzzDuration = buzzDuration;
        }
    }

    public static void initialize(final Options options) {
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalOutput pin =
                gpio.provisionDigitalOutputPin(options.gatePin, "GateBuzzer", PinState.HIGH);
        pin.setShutdownOptions(true, PinState.HIGH);
        INSTANCE = new GateBuzzer(options, pin);
        LOG.info("Gate Buzzer initialized");
    }

    public static GateBuzzer getInstance() {
        return INSTANCE;
    }

    private static GateBuzzer INSTANCE;

    private final Options options;
    private final GpioPinDigitalOutput pin;

    public void buzzDoor() throws InterruptedException {
        final long buzzDuration = options.buzzDuration;
        LOG.info("Buzzing gate for " + buzzDuration + "ms");
        pin.low();
        Thread.sleep(buzzDuration);
        pin.high();
        LOG.info("Gate buzz complete");
    }

    private GateBuzzer(final Options options, GpioPinDigitalOutput pin) {
        this.options = options;
        this.pin = pin;
    }

}
