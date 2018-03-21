package com.gpsnyder.home.app.resource;

import com.codahale.metrics.annotation.Timed;
import com.gpsnyder.home.app.views.HomeView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {

    private static final Logger LOG = LoggerFactory.getLogger(HomeView.class);

    @GET
    @Timed
    public HomeView sayHello(@Context HttpServletRequest httpRequest) {
        return new HomeView();
    }
}