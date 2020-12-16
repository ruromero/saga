package org.acme.sagas.resource;

import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.acme.sagas.service.ReservationService;

@Path("/")
public class ReservationResource {

    @Inject
    ReservationService service;

    @GET
    @Path("/{reservation}")
    public Map<String, String> list(@PathParam("reservation") String type) {
        return service.getReservations(type);
    }
}
