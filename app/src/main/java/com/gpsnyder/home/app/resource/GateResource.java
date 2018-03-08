package com.gpsnyder.home.app.resource;

import com.codahale.metrics.annotation.Timed;
import com.gpsnyder.home.app.model.GateResponse;
import com.gpsnyder.home.gate.GateBuzzer;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;

@Path("/gate")
@Produces(MediaType.TEXT_HTML)
public class GateResource {

    private static final String RESPONSE_TPL
            = "<html><body><h1>Gate Buzz</h1><p>Attempted at: %s</p><p>Successful: %s</p></body></html>";

    @GET
    @Timed
    public String sayHello() {
        return "<html><body><form method='post'><input type='submit' value='Buzz Gate'><form></body></html>";
    }

    @POST
    @Timed
    public String openGate() throws InterruptedException {
        final GateResponse out = new GateResponse();
        try {
            final GateBuzzer instance = GateBuzzer.getInstance();
            instance.buzzDoor();
            out.setSuccessful(true);
        } catch (Throwable t) {
            out.setSuccessful(false);
        }
        out.setTimestamp(LocalDateTime.now().toString());
        return String.format(RESPONSE_TPL, out.getTimestamp(), out.isSuccessful());
    }

}