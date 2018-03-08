package com.gpsnyder.home.app.resource;

import com.codahale.metrics.annotation.Timed;
import com.gpsnyder.home.app.model.HomeResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class HomeResource {

    private final AtomicLong counter;

    public HomeResource() {
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public HomeResponse sayHello() {
        return new HomeResponse(counter.getAndIncrement(), "Hi!");
    }
}