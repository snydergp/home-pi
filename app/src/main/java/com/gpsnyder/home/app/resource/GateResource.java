package com.gpsnyder.home.app.resource;

import com.codahale.metrics.annotation.Timed;
import com.gpsnyder.home.app.model.GateResponse;
import com.gpsnyder.home.app.views.GateView;
import com.gpsnyder.home.gate.GateBuzzer;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;

@Path("/gate")
public class GateResource {

    @GET
    @Timed
    @Produces(MediaType.TEXT_HTML)
    public GateView get() {
        return new GateView();
    }

    @POST
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public GateResponse openGate(@Context HttpServletRequest httpRequest) {
        final GateResponse out = new GateResponse();
        out.setTimestamp(LocalDateTime.now().toString());
        out.setUser(httpRequest.getHeader("authuser"));
        try {
            final GateBuzzer instance = GateBuzzer.getInstance();
            instance.buzzDoor();
            out.setSuccessful(true);
        } catch (Throwable t) {
            out.setErrorMessage(t.getMessage());
        }
        return out;
    }

}